
<!-- <img src="./images/Medibot.png"  width="700" height="370"> -->

# 💊Medibot : 의약품 정보 제공 챗봇
<p align="center">
  <img src="https://github.com/2023-Hongik-Medibot/SpringServer/assets/76999875/a000e22a-9575-46ff-b1e2-d52e7c0a4a03" width="300" height="300">
</p>
<!-- ![Medibot](https://github.com/2023-Hongik-Medibot/SpringServer/assets/76999875/a000e22a-9575-46ff-b1e2-d52e7c0a4a03)
 -->
 
## 👏프로젝트 소개

**Medibot은 의약품에 대한 정보를 제공하는 웹 서비스이다.**

다음과 같은 기능들이 있다.
- 의약품에 대한 주의사항, 복용법, 부작용, 효과를 알 수 있다.
- 이미지를 통해 의약품 정보를 얻을 수 있다.
- 입력한 위치에 대한 병원 정보를 얻을 수 있다.
- 입력한 위치에 대한 약국 정보를 얻을 수 있다.
<br>

## 👩팀원 소개
<div align="center">

| **이지민** | **김민영** | 
| :------: |  :------: | 
| [<img src="https://github.com/2023-Hongik-Medibot/SpringServer/assets/76999875/46bfc49a-0204-4a27-9081-da7b3de8f68f" height=150 width=150> <br/> @dlwlals1289](https://github.com/dlwlals1289) | [<img src="" height=150 width=150> <br/> @myqewr](https://github.com/myqewr) |

</div>

## 🛠️개발 환경

**FRONTEND** : React, Javascript

**BACKEND** : Springboot, Java

**AI** : Pytorch, Python

**협업 툴** : Notion

**서비스 배포** : AWS

<br>

## 📁프로젝트 구조
```
├── README.md
├── gradle/wrapper
├── .gitignore
├── images
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
│
└── src
     ├── main/java/Medibot
     │     ├── Controller
     │     │     ├──  AwsS3Controller.java
     │     │     ├──  ExceptionController.java
     │     │     └──  IndexController.java
     │     ├── Domain
     │     │     └── Pill.java  
     │     ├── Dto
     │     │     ├──  AIPillINameAndImageDto.java
     │     │     ├──  AIQuestionDto.java
     │     │     ├──  AnswerDto.java
     │     │          .
     │     │          .
     │     │          .
     │     ├── Exception
     │     │     ├──  MedibotException.java
     │     │     ├──  NotFoundIntentException.java
     │     │     └──  NotFoundPillException.java
     │     ├── Handler
     │     │     └──  ExceptionHandler.java
     │     ├── Service
     │     │     ├──  AwsS3Service.java
     │     │     ├──  ImageS3Service.java
     │     │     ├──  KakaoRestApiService.java
     │     │     ├──  PillService.java
     │     │     └──  QuestionService.java
     │     ├── Filter
     │     │     └──  CorsFilter.java
     │     ├── Exception
     │     │
     │     │
     │     ├── AwsS3Config.java
     │     ├── KakaoRestApi.java
     │     ├── MedibotApplication.java
     │     └── WebMvcConfig.java
     └── test/java/Medibot
```
<br>

## 💻개발 기간
- 기획 : 2023.01 - 2023.03
- 개발 : 2023.04 - 2023.11
<br>

## 🎉프로젝트 후기

**이지민** :

**김민영** :

## 👍프로그램 결과
<img width="1512" alt="medibot_image1" src="https://github.com/2023-Hongik-Medibot/SpringServer/assets/76999875/ba9707d3-d5cd-4754-b459-d48e3c58086a">
<img width="1512" alt="medibot_image2" src="https://github.com/2023-Hongik-Medibot/SpringServer/assets/76999875/a5a6f563-e51a-4c47-8d7d-f71346214913">
<img width="1512" alt="medibot1" src="https://github.com/2023-Hongik-Medibot/SpringServer/assets/76999875/7288aadf-db6b-49ca-9bcc-f3d59e2e11df">
<img width="1512" alt="medibot2" src="https://github.com/2023-Hongik-Medibot/SpringServer/assets/76999875/19703452-fffe-4ff3-9c51-42dd9634c428">
<img width="1512" alt="medibot_hospital" src="https://github.com/2023-Hongik-Medibot/SpringServer/assets/76999875/09eede03-e30e-4776-a187-600ce72a4b9c">
<img width="1512" alt="medibot_pharmacy" src="https://github.com/2023-Hongik-Medibot/SpringServer/assets/76999875/f5ec1e2e-5d50-44c5-97e1-84f768656761">
