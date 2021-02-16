# 자바 프로그래밍 텀 프로젝트 🙌🙌

# 프로젝트 명 : 헝크리 샤크 (Hungry Shark)🦈🦈🦈🦈🦈🦈🦈🦈
## RULE🦈🦈🦈🦈🦈🦈🦈🦈
- Java로 구현한 헝크리 샤크.
- 방향키(➡⬅⬆⬇↗↘↙↖) 움직이며 적들을 피해다니며 물고기들을 먹는 게임.
- 적(폭탄, 나보다 큰 상어), 물고기가 특정 시간마다 새로 생성.
- 물고기 or 나보다 작은 적을 먹으면 점수 획득 + 크기 증가. 
- 폭탄 or 나보다 큰 적과 부딪히면 HP감소 + 크기 감소. 
- HP가 0가 되면 게임종료.
- 왼쪽 아래 부스터 사용가능, 레벨당 1번씩.

---
## 시작화면
![image](https://user-images.githubusercontent.com/60773356/108069342-9b75c200-70a6-11eb-82f6-393ec0143ab3.png)
* 시작 버튼을 누르면 **게임 start!!**

## 게임화면
### 1. 나보다 작은 물고기 / 사람 먹기
![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/60773356/108070507-f1973500-70a7-11eb-8932-11b2061ac341.gif)
* 크기 증가, 점수 증가
* 리얼한 blood effect와 비명소리 + 아그작 씹어먹는 소리

### 2. 부스터 사용
![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/60773356/108070875-60748e00-70a8-11eb-9423-002dbf413e48.gif)

### 3. 레벨업!!!!🤸‍🤸‍🤸‍
![ezgif com-gif-maker (3)](https://user-images.githubusercontent.com/60773356/108070979-813ce380-70a8-11eb-9755-6eccc2b2293b.gif)
* 특정 점수 넘어가면 Level Up!
* 황금 상어로 변신!!!

### 4. 적을 만났닷!!..
![ezgif com-gif-maker](https://user-images.githubusercontent.com/60773356/108071528-2c4d9d00-70a9-11eb-9bce-4a9a877b83af.gif)
* HP 감소, 크기 감소
* 충돌 사운드
* 폭탄과 부딪히면 boom사운드

## 게임화면
![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/60773356/108071750-720a6580-70a9-11eb-911b-3073720056a2.gif)
* HP가 0가 되면 게임종료
* 친숙한 슈퍼마리오 브금과 함께 점수 표시
* Restart버튼 누를 시 처음부터 

---
---

## 💻💻코드 설명💻💻
### res패키지
* 각종 이미지파일 + 사운드 효과
### -   DiagonallyMoving  /  HorizontallyMovie.java  /   VerticallyMoving.java   
* enemy(폭탄과 상어들)와 food(작은 물고기들과 사람들)의 움직임을 위한 클래스
* **새로 생성되는 enemy와 food들은 이 셋중 랜덤하게 하나의 moving을 가지고 움직인다.**
* 수평으로 왔다갔다 or 수직으로 오르락내리락 or 대각선으로 난리법석 


### - Moving.java
* **enemy와 food들의 시작 좌표를 설정해주는 클래스이고 enemy객체 food객체에 대한 margin값이나 경계값 크기등을 설정해주는 클래스.**
* Math.random()을 통해 x, y좌표를 랜덤으로 설정.
* 크기도 랜덤

### - Moving2.java
* **내 상어에 대한 세부정보 ==> 시작점(랜덤 아님, 고정 좌표), margin, 크기 등**

#### + Moving / Moving2 둘다 매개변수를 받음.(앞서말한 margin 등등)


### - Panels.java
* **눈에 보여지는 화면을 그리는 클래스 ==> View라고 할수 있겠다.**
* JFrame안에 각각의 JPanel(게임 시작 화면, 게임 화면, 게임 종료 화면)을 쌓아 두고, CardLayout을 통해 특정 조건에 맞을 때만 특정 Panel을 보여주는 식으로 구현했다.
* KeyEvent를 통해 입력받은 대로 내 상어를 움직인다.
* enemy / food 들은 자동으로 생성되어 ArrayList에 담아지고 이를 계속 뿌려주는 형식이다.
* 생동감있는 상어를 나타내기 위해 입력받은 방향키에 따라 상어의 이미지를 바꿔주었다. ===> 무슨 의미냐면 ➡을 누를때는 상어가 오른쪽을 보고 있는 이미지 즉, 방향키에 맞는 상어의 모습을 구현하였다.(대각선 또한 가능 - 8방향을 구현)

