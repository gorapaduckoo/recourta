1. gitlab 소스 클론 이후 빌드 및 배포할 수 있는 작업문서
사용한 JVM, 웹서버 버전, 설정

JVM : 1.8버전

웹서버 : 스프링부트 2.7.1 내장 톰캣서버

서비스 포트번호 : 443 (https) 8081 (backend) 3715(frontend)

IDE

Intellij 2021.1.3

vscode 1.70.0

빌드 시 사용되는 환경 변수 등의 주요 내용 상세 기재

applicaton.properties 파일

배포 정보, 배포시 특이사항 및 openvidu 환경변수 기재

배포.pdf

jar 파일을 컨테이너로 실행할 때 필요한 설정 파일

cd backend cd Recourta docker rm -f backend docker build -t backend:latest . docker run --name backend --network="host" -d -p 8081:8081 -v /media/uploads/img:/media/uploads/img -v /media/uploads/model:/media/uploads/model backend

2. 프로젝트에서 사용하는 외부 서비스 정보 문서
얼굴 인식을 위한 face-api 사용 https://github.com/justadudewhohacks/face-api.js/

음성 인식을 위한 Web Speech API 사용 https://developer.mozilla.org/en-US/docs/Web/API/Web_Speech_API

3. DB 덤프 파일 최신본
recourta_dump.sql

4. 시연 시나리오
시연시나리오.pdf
