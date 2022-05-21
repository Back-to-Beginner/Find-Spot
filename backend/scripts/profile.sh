#!/usr/bin/env bash

# profile.sh
# 미사용 중인 profile을 잡는다.

function find_idle_profile() {
  # curl 결과로 연결할 서비스 결정
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ]; then # 400 보다 크면 (즉, 40x/50x 에러 모두 포함)
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  # IDLE_PROFILE : nginx와 연결되지 않은 profile
  if [ ${CURRENT_PROFILE} == real1 ]; then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi

  # bash script는 값의 반환이 안된다.
  # echo로 결과 출력 후, 그 값을 잡아서 사용한다.
  echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port() {
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]; then
    echo "8081"
  else
    echo "8082"
  fi
}
