from flask import Flask, request
from werkzeug.utils import secure_filename
import cv2 as cv2
import numpy as np
from skiㅊmage.metrics import structural_similarity as ssim

app = Flask(__name__)

# // 비교 알고리즘의 이름들을 리스트에 저장
methods = ['CORREL',  # cv2.HISTCMP_CORREL: 상관관계 (1: 완전 일치, -1: 완전 불일치, 0: 무관계)
           'CHISQR',  # cv2.HISTCMP_CHISQR: 카이제곱 (0: 완전 일치, 무한대: 완전 불일치)
           'INTERSECT',  # cv2.HISTCMP_INTERSECT: 교차 (1: 완전 일치, 0: 완전 불일치 - 1로 정규화한 경우)
           'BHATTACHARYYA',  # cv2.HISTCMP_BHATTACHARYYA 값이 작을수록 유사한 것으로 판단
           'EMD']


@app.route('/api/compare', methods=['POST'])
def check_simular():
    if request.method == 'POST':
        image1 = request.files['file1']
        image2 = request.files['file2']
        image1.save(secure_filename(image1.filename))
        image2.save(secure_filename(image2.filename))

        # 이미지 읽어오기
        imgs = [cv2.imread(image1.filename), cv2.imread(image2.filename)]

        hists = []
        grays = []
        for img in imgs:
            hists.append(image_to_hsv(img))
            grays.append(image_to_gray(img))

        result = []

        # // 5회 반복(5개 비교 알고리즘을 모두 사용)
        for index, name in enumerate(methods):
            # // 비교 알고리즘 이름 출력(문자열 포맷팅 및 탭 적용)
            print('%-10s' % name)

            # // 2회 반복(2장의 이미지에 대해 비교 연산 적용)
            ret = cv2.compareHist(hists[0], hists[1], index)

            if index == cv2.HISTCMP_INTERSECT:  # // 교차 분석인 경우
                ret = ret / np.sum(hists[0])  # // 원본으로 나누어 1로 정규화

            result.append(ret)
            print("img%d :%7.2f" % (index + 1, ret))  # // 비교 결과 출력

        (score, diff) = ssim(grays[0], grays[1], full=True)
        result.append(score)
        print(f"Similarity: {score:.5f}")

        score = 0
        if result[0] > 0.8:
            score += 1
        if result[1] < 100:
            score += 1
        if result[2] > 0.7:
            score += 1
        if result[3] < 0.4:
            score += 1
        if result[4] < 100:
            score += 1
        if result[5] > 0.4:
            score += 1

        print(score)
        if score > 3:
            return 'similar image'

    return 'fail!'


def image_to_hsv(img):
    # BGR 이미지를 HSV 이미지로 변환
    hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
    hsv = cv2.resize(hsv, dsize=(1080, 1080))
    # 히스토그램 연산(파라미터 순서 : 이미지, 채널, Mask, 크기, 범위)
    hist = cv2.calcHist([hsv], [0, 1], None, [1080, 1080], [0, 1080, 0, 1080])
    # 정규화(파라미터 순서 : 정규화 전 데이터, 정규화 후 데이터, 시작 범위, 끝 범위, 정규화 알고리즘)
    cv2.normalize(hist, hist, 0, 1, cv2.NORM_MINMAX)
    # hists 리스트에 저장
    return hist


def image_to_gray(img):
    # // BGR 이미지를 GRAY 이미지로 변환
    img = cv2.resize(img, [1080, 1080])
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    return gray


if __name__ == '__main__':
    app.run()
