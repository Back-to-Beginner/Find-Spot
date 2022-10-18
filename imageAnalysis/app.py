from flask import Flask, request
import cv2 as cv2
import numpy as np
import urllib
from skimage.metrics import structural_similarity as ssim
import joblib

app = Flask(__name__)

# // 비교 알고리즘의 이름들을 리스트에 저장
methods = ['CORREL',  # cv2.HISTCMP_CORREL: 상관관계 (1: 완전 일치, -1: 완전 불일치, 0: 무관계)
           'CHISQR',  # cv2.HISTCMP_CHISQR: 카이제곱 (0: 완전 일치, 무한대: 완전 불일치)
           'INTERSECT',  # cv2.HISTCMP_INTERSECT: 교차 (1: 완전 일치, 0: 완전 불일치 - 1로 정규화한 경우)
           'BHATTACHARYYA',  # cv2.HISTCMP_BHATTACHARYYA 값이 작을수록 유사한 것으로 판단
           'EMD']

model = joblib.load('./model/model.joblib')


@app.route('/api-python/v1/test', methods=['GET'])
def hello():
    if request.method == 'GET':
        return 'True'
    else:
        'False'


@app.route('/api-python/v1/compare', methods=['POST'])
def check_simular():
    if request.method == 'POST':
        data = request.get_json()
        res = check_simular(data.get("mission"), data.get("trial"), data.get("slice"))
        pred = model.predict([res])
        if pred[0] == 0:
            return True
        return False


def url_to_image(url, slice):
    resp = urllib.request.urlopen(url)
    image = np.asarray(bytearray(resp.read()), dtype="uint8")
    image = cv2.imdecode(image, cv2.IMREAD_COLOR)
    image = image[slice[0]:slice[1], slice[2]:slice[3]].copy()  # 세로, 가로
    return image


def slice_image(img, slice):
    img = cv2.resize(img, [360, 360])
    return img[slice[0]:slice[1], slice[2]:slice[3]]


def check_simular(mission, trial, slice):
    # 이미지 읽어오기
    imgs = [url_to_image(mission, slice), url_to_image(trial, slice)]
    # imgs = [slice_image(mission, slice), slice_image(trial, slice)]

    hists = []
    grays = []
    for img in imgs:
        hists.append(image_to_hsv(img))
        grays.append(image_to_gray(img))

    result = [
        cv2.compareHist(hists[0], hists[1], 0),
        cv2.compareHist(hists[0], hists[1], 2) / np.sum(hists[0]),
        cv2.compareHist(hists[0], hists[1], 3),
        ssim(grays[0], grays[1], full=True)
    ]
    # cv2.HISTCMP_CORREL: 상관관계 (1: 완전 일치, -1: 완전 불일치, 0: 무관계)
    # cv2.HISTCMP_INTERSECT: 교차 (1: 완전 일치, 0: 완전 불일치 - 1로 정규화한 경우)
    # cv2.HISTCMP_BHATTACHARYYA 값이 작을수록 유사한 것으로 판단
    # ssim 1에 근접할 수록 유사한 이미지

    # print(result[0], result[1], result[2], result[3][0])

    return result[0] + result[1] - result[2], result[3][0]


def image_to_hsv(img):
    # BGR 이미지를 HSV 이미지로 변환
    hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
    hsv = cv2.resize(hsv, dsize=(360, 360))
    # 히스토그램 연산(파라미터 순서 : 이미지, 채널, Mask, 크기, 범위)
    hist = cv2.calcHist([hsv], [0, 1], None, [360, 360], [0, 360, 0, 360])
    # 정규화(파라미터 순서 : 정규화 전 데이터, 정규화 후 데이터, 시작 범위, 끝 범위, 정규화 알고리즘)
    cv2.normalize(hist, hist, 0, 1, cv2.NORM_MINMAX)
    # hists 리스트에 저장
    return hist


def image_to_gray(img):
    # // BGR 이미지를 GRAY 이미지로 변환
    img = cv2.resize(img, [360, 360])
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    return gray


if __name__ == '__main__':
    app.run()
