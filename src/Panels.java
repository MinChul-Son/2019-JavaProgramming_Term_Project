import java.applet.AudioClip;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panels {
	JFrame frame = new JFrame();
	JButton start = new JButton();
	JButton boost = new JButton();
	JButton end = new JButton("");
	JLabel scoreLabel; // 게임패널의 점수
	JLabel scoreLabel2;// 게임오버패널의 점수

	JPanel coverPanel;
	GamePanel gamePanel;
	GameOverPanel gameOverPanel;
	CardLayout card = new CardLayout();// 카드레이아웃
	Timer goAnime;// 애니메이션 타이머
	Timer goClock;// 일정 주기마다 물고기들과 사람과 적을 추가하는 타이머
	Timer goBoost;// 부스터를 위한 타이머
	Timer goLevelup;// 레벨업를 위한 타이머
	Timer goLevelupPic;// 레벨업 이미지를 위한 타이머
	Timer goMissile;// 미사일을 위한 타이머
	ClockListener clockListener;
	DirectionListener keyListener;
	ClockListener2 clockListener2;
	ClockListener3 clockListener3;
	ClockListener4 clockListener4;
	ClockListener5 clockListener5;
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;

	private final int FOOD_MAKING_TIME = 8;
	private final int ENEMY_MAKING_TIME = 15;
	private final int SWIMMER_MAKING_TIME = 8;

	private final String MAIN_PIC = "/res/cover.png";
	private final String SHARKRIGHT_PIC = "/res/movingsharkright.gif";
	private final String SHARKLEFT_PIC = "/res/movingsharkleft.gif";
	private final String SHARKUP_PIC = "/res/movingsharkup.gif";
	private final String SHARKDOWN_PIC = "/res/movingsharkdown.gif";
	private final String SHARKLEFTDOWN_PIC = "/res/movingsharkleftdown.gif";
	private final String SHARKRIGHTDOWN_PIC = "/res/movingsharkrightdown.gif";
	private final String SHARKLEFTUP_PIC = "/res/movingsharkleftup.gif";
	private final String SHARKRIGHTUP_PIC = "/res/movingsharkrightup.gif";
	private final String GOLDSHARKRIGHT_PIC = "/res/goldsharkright.gif";
	private final String GOLDSHARKLEFT_PIC = "/res/goldsharkleft.gif";
	private final String GOLDSHARKUP_PIC = "/res/goldsharkup.gif";
	private final String GOLDSHARKDOWN_PIC = "/res/goldsharkdown.gif";
	private final String GOLDSHARKLEFTDOWN_PIC = "/res/goldsharkleftdown.gif";
	private final String GOLDSHARKRIGHTDOWN_PIC = "/res/goldsharkrightdown.gif";
	private final String GOLDSHARKLEFTUP_PIC = "/res/goldsharkleftup.gif";
	private final String GOLDSHARKRIGHTUP_PIC = "/res/goldsharkrightup.gif";
	private final String MISSILE_PIC = "/res/missile.gif";

	private final String BLOBFISH_PIC = "/res/blob fish.png";
	private final String FISH_PIC = "/res/fish.png";
	private final String SARDINE_PIC = "/res/sardine.png";
	private final String TUNA_PIC = "/res/tuna.png";
	private final String BARRACUDA_PIC = "/res/barracuda.png";
	private final String SAILFISH_PIC = "/res/sailfish.png";
	private final String ENEMYSHARK_PIC = "/res/enemyshark.png";
	private final String MEGALODON_PIC = "/res/megalodon.png";
	private final String SUB_PIC = "/res/sub.png";
	private final String WHALE_PIC = "/res/whale.png";
	private final String TOXIC_PIC = "/res/toxic.png";
	private final String MINE_PIC = "/res/mine.png";
	private final String GAME_PIC = "/res/game1.jpg";
	private final String BOOSTOFF_PIC = "/res/boostoff.png";
	private final String BOOSTON_PIC = "/res/booston.png";
	private final String SWIMMER_PIC = "/res/swimmer.gif";
	private final String SWIMMER2_PIC = "/res/swimmer2.gif";
	private final String LIFEBAR1_PIC = "/res/lifebar1.png";
	private final String LIFEBAR2_PIC = "/res/lifebar2.png";
	private final String GAMEOVER_PIC = "/res/gameover.gif";
	private final String RESTART_PIC = "/res/restart.png";
	private final String LEVELUP_PIC = "/res/levelup.gif";
	private final String BOOM_PIC = "/res/boom.gif";
	private final String BLOOD_PIC = "/res/blood.gif";

	private final String START_MUSIC = "/res/startmusic.wav";
	private final String EAT_SOUND = "/res/eatsound.wav";
	private final String DEAD_SOUND = "/res/deadsound.wav";
	private final String SCREAM_SOUND = "/res/screamsound.wav";
	private final String SCREAM2_SOUND = "/res/screamsound2.wav";
	private final String GAMEOVER_SOUND = "/res/gameovermusic.wav";
	private final String LEVELUP_SOUND = "/res/levelup.wav";
	private final String BOMB_SOUND = "/res/bombsound.wav";
	private AudioClip startMusic;
	private AudioClip eatSound;
	private AudioClip deadSound;
	private AudioClip screamSound;
	private AudioClip screamSound2;
	private AudioClip gameOverMusic;
	private AudioClip levelUp;
	private AudioClip bombSound;

	private int STEPS = 10;
	private int STEPS2 = 6;
	int times = 0;// 물고기, 적, 수영하는사람이 생성되기 위한 시간변수
	int times2 = 0;// 부스터를 사용하면 5초뒤에 꺼지기 위한 시간변수
	int time2 = 0;// levelup.gif를 그리고 없애기 위한 시간변수
	int time3 = 0;// levelup.gif를 그리고 없애기 위한 시간변수
	int x = 0;// 부스트 on off를 위한 변수
	//int y = 0;// 폭탄or미사일과 충돌했을때 알기위한 변수
	int blood = 0;// 물고기를 먹었을 때 피가 나오게 하기위한 변수
	int life = 200;
	int shark_width = 200, shark_height = 100;// 상하좌우에서의 상어 폭,높이
	int shark_width2 = 235, shark_height2 = 225;// 대각선에서의 상어 폭,높이
	int boom_x = 0;// boom.gif를 그려줄 x좌표
	int boom_y = 0;// boom.gif를 그려줄 y좌표
	int blood_x = 0;// blood.gif를 그려줄 x좌표
	int blood_y = 0;// blood.gif를 그려줄 y좌표
	int level = 1;// 레벨업을 위한 변수, 초기에는 1, 레벨업하면 2가되고 황금상어로 바뀜
	int sub_make = 0;// 잠수함이 만들어졌는지 알기위한 변수
	int sub_x = 0;// 미사일을 잠수함 밑에 그리기 위해 잠수함의 x좌표 저장하는 변수
	int sub_y = 0;// 미사일을 잠수함 밑에 그리기 위해 잠수함의 y좌표 저장하는 변수
	int sub_width = 0;// 잠수함의 폭
	int sub_height = 0;// 잠수함의 높이

	private final int MARGIN = 50;// 물고기의 margin
	private final int MARGIN3 = 30;// 상어의 margin
	private final int MARGIN2 = 80;// 적의 margin
	private final int WIN_WIDTH = 1350;
	private final int WIN_HEIGHT = 900;
	private final int SPEED = 80;// goAnime와 goMissile 타이머의 속도
	int SCORE = 0;
	int gamePanelWidth, gamePanelHeight;
	int gameOverPanelWidth, gameOverPanelHeight;
	ArrayList<Moving> enemyList;
	ArrayList<Moving> foodList;

	Moving2 shark;

	public static void main(String[] args) {
		Panels gui = new Panels();
		gui.go();
	}

	public void go() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(card);
		gamePanel = new GamePanel();
		gameOverPanel = new GameOverPanel();
		gameOverPanel.setBounds(0, 0, WIN_WIDTH, WIN_HEIGHT);
		gamePanel.setBounds(0, 0, WIN_WIDTH, WIN_HEIGHT);
		coverPanel = new CoverPanel();
		coverPanel.setLayout(null);
		gamePanel.setLayout(null);
		gameOverPanel.setLayout(null);
		scoreLabel = new JLabel("점수 : 0점");
		scoreLabel2 = new JLabel("점수 : 0점");
		frame.add(coverPanel);
		gamePanelWidth = gamePanel.getWidth();
		gamePanelHeight = gamePanel.getHeight();
		shark = new Moving2(getClass().getResource(SHARKRIGHT_PIC), "sharkright", 300, 300, MARGIN, STEPS2,
				gamePanelWidth, gamePanelHeight, shark_width, shark_height);
		gameOverPanelWidth = gameOverPanel.getWidth();
		gameOverPanelHeight = gameOverPanel.getHeight();
		clockListener = new ClockListener();
		goClock = new Timer(1000, clockListener);
		goAnime = new Timer(SPEED, new AnimeListener());
		goBoost = new Timer(1000, new ClockListener2());
		goLevelup = new Timer(1000, new ClockListener3());
		goLevelupPic = new Timer(1000, new ClockListener4());
		goMissile = new Timer(SPEED, new ClockListener5());
		coverPanel.add(start);
		end.setBounds(300, 450, 800, 200);
		end.setBorderPainted(false);
		end.setContentAreaFilled(false);
		end.setFocusPainted(false);
		start.setBounds(550, 640, 300, 200);
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setFocusPainted(false);
		end.addActionListener(new EndListener());
		gamePanel.add(boost);
		gamePanel.add(scoreLabel);
		gameOverPanel.add(scoreLabel2);
		scoreLabel.setBounds(1650, 0, 500, 200);
		scoreLabel.setFont(new Font("Serif", Font.BOLD, 40));
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel2.setBounds(550, 0, 500, 200);
		scoreLabel2.setFont(new Font("Serif", Font.BOLD, 40));
		scoreLabel2.setForeground(Color.WHITE);

		boost.setBounds(30, 680, 100, 100);
		boost.setBorderPainted(false);
		boost.setContentAreaFilled(false);
		boost.setFocusPainted(false);
		start.addActionListener(new StartListener());
		boost.addActionListener(new BoostListener());

		try {

			startMusic = JApplet.newAudioClip(getClass().getResource(START_MUSIC));
			eatSound = JApplet.newAudioClip(getClass().getResource(EAT_SOUND));
			deadSound = JApplet.newAudioClip(getClass().getResource(DEAD_SOUND));
			screamSound = JApplet.newAudioClip(getClass().getResource(SCREAM_SOUND));
			screamSound2 = JApplet.newAudioClip(getClass().getResource(SCREAM2_SOUND));
			gameOverMusic = JApplet.newAudioClip(getClass().getResource(GAMEOVER_SOUND));
			levelUp = JApplet.newAudioClip(getClass().getResource(LEVELUP_SOUND));
			bombSound = JApplet.newAudioClip(getClass().getResource(BOMB_SOUND));

		} catch (Exception e) {
			System.out.println("음향 파일 로딩 실패");
		}

		startMusic.play();
		gamePanel.addKeyListener(new DirectionListener());
		gamePanel.setFocusable(false);
		gameOverPanel.add(end);
		gameOverPanel.setFocusable(false);

		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		frame.getContentPane().add(coverPanel, "n1");
		frame.getContentPane().add(gamePanel, "n2");
		frame.getContentPane().add(gameOverPanel, "n3");
		frame.setVisible(true);

	}

	private void enemyReady() {
		enemyList = new ArrayList<Moving>();
	}

	private void foodReady() {
		foodList = new ArrayList<Moving>();
		foodList.add(new DiagonallyMoving(getClass().getResource(SARDINE_PIC), "fish", MARGIN,
				(int) (Math.random() * 60) + 50, STEPS, 1850, 900));
		foodList.add(new DiagonallyMoving(getClass().getResource(TUNA_PIC), "fish", MARGIN,
				(int) (Math.random() * 60) + 50, STEPS, 1850, 900));
		foodList.add(new HorizontallyMoving(getClass().getResource(SAILFISH_PIC), "fish", MARGIN,
				(int) (Math.random() * 60) + 50, STEPS, 1850, 900));
		foodList.add(new DiagonallyMoving(getClass().getResource(SWIMMER_PIC), "swimmer1", MARGIN,
				(int) (Math.random() * 60) + 50, STEPS, 1850, 900));
		foodList.add(new DiagonallyMoving(getClass().getResource(SWIMMER2_PIC), "swimmer2", MARGIN,
				(int) (Math.random() * 60) + 50, STEPS, 1850, 900));
	}

	class GamePanel extends JPanel {
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(getClass().getResource(GAME_PIC)).getImage();
			g.drawImage(image, 0, 0, 2000, 1000, this);
			Image image1 = new ImageIcon(getClass().getResource(BOOSTON_PIC)).getImage();
			Image image2 = new ImageIcon(getClass().getResource(BOOSTOFF_PIC)).getImage();
			Image image3 = new ImageIcon(getClass().getResource(LIFEBAR1_PIC)).getImage();
			g.drawImage(image3, 1650, 0, 200, 100, this);
			Image image4 = new ImageIcon(getClass().getResource(LIFEBAR2_PIC)).getImage();
			g.drawImage(image4, 1650, 0, life, 100, this);
			Image image5 = new ImageIcon(getClass().getResource(LEVELUP_PIC)).getImage();
			Image image6 = new ImageIcon(getClass().getResource(BOOM_PIC)).getImage();
			Image image7 = new ImageIcon(getClass().getResource(BLOOD_PIC)).getImage();
			if (time2 > 0)
				g.drawImage(image5, 600, 0, 500, 500, this);
			/*if (y == 1)
				g.drawImage(image6, boom_x, boom_y, 500, 500, this);*/
			if (blood == 1)
				g.drawImage(image7, blood_x, blood_y, 300, 300, this);

			if (up == true)
				shark.y -= STEPS2;
			if (down == true)
				shark.y += STEPS2;
			if (left == true)
				shark.x -= STEPS2;
			if (right == true)
				shark.x += STEPS2;
			if (x == 0)
				g.drawImage(image1, 0, 650, 150, 150, this);

			else
				g.drawImage(image2, 0, 650, 150, 150, this);

			for (Moving m : enemyList) {
				m.draw(g, this);
			}
			for (Moving m : foodList) {
				m.draw(g, this);
			}
			shark.draw2(g, this);
			repaint();
		}
	}

	class CoverPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(getClass().getResource(MAIN_PIC)).getImage();
			g.drawImage(image, -100, 0, 1600, WIN_HEIGHT, this);
		}
	}

	class GameOverPanel extends JPanel {
		public void paintComponent(Graphics g) {
			Image image = new ImageIcon(getClass().getResource(GAMEOVER_PIC)).getImage();
			g.drawImage(image, 0, 0, WIN_WIDTH, WIN_HEIGHT, this);
			Image image1 = new ImageIcon(getClass().getResource(RESTART_PIC)).getImage();
			g.drawImage(image1, 300, 350, 800, 450, this);
			scoreLabel2.setText("점수 : " + SCORE + "점");
		}
	}

	class StartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			card.show(frame.getContentPane(), "n2");
			frame.setSize(1930, 1000);
			enemyReady();
			foodReady();
			goAnime.start();
			goClock.start();
			goLevelup.start();
			goMissile.start();
			gamePanel.addKeyListener(new DirectionListener());
			gamePanel.setFocusable(true);
			gamePanel.requestFocus();
		}
	}

	class EndListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			card.show(frame.getContentPane(), "n1");
			resetGame();
		}
	}

	class BoostListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			x = 1;
			goBoost.start();
			boostOn();
		}
	}

	public void changeShark() {
		if (up == true && level == 1)
			shark = new Moving2(getClass().getResource(SHARKUP_PIC), "sharkup", shark.x, shark.y, MARGIN, STEPS2, 1850,
					900, shark_height, shark_width);
		if (down == true && level == 1)
			shark = new Moving2(getClass().getResource(SHARKDOWN_PIC), "sharkdown", shark.x, shark.y, MARGIN, STEPS2,
					1850, 900, shark_height, shark_width);
		if (left == true && level == 1)
			shark = new Moving2(getClass().getResource(SHARKLEFT_PIC), "sharkleft", shark.x, shark.y, MARGIN, STEPS2,
					1850, 900, shark_width, shark_height);
		if (right == true && level == 1)
			shark = new Moving2(getClass().getResource(SHARKRIGHT_PIC), "sharkright", shark.x, shark.y, MARGIN, STEPS2,
					1850, 900, shark_width, shark_height);
		if (right == true && up == true && level == 1)
			shark = new Moving2(getClass().getResource(SHARKRIGHTUP_PIC), "sharkrightup", shark.x, shark.y, MARGIN3,
					STEPS2, 1850, 900, shark_width2, shark_height2);
		if (right == true && down == true && level == 1)
			shark = new Moving2(getClass().getResource(SHARKRIGHTDOWN_PIC), "sharkrightdown", shark.x, shark.y, MARGIN3,
					STEPS2, 1850, 900, shark_width2, shark_height2);
		if (left == true && up == true && level == 1)
			shark = new Moving2(getClass().getResource(SHARKLEFTUP_PIC), "sharkleftup", shark.x, shark.y, MARGIN3,
					STEPS2, 1850, 900, shark_width2, shark_height2);
		if (left == true && down == true && level == 1)
			shark = new Moving2(getClass().getResource(SHARKLEFTDOWN_PIC), "sharkleftdown", shark.x, shark.y, MARGIN3,
					STEPS2, 1850, 900, shark_width2, shark_height2);
		if (up == true && level == 2)
			shark = new Moving2(getClass().getResource(GOLDSHARKUP_PIC), "sharkup", shark.x, shark.y, MARGIN, STEPS2,
					1850, 900, shark_height, shark_width);
		if (down == true && level == 2)
			shark = new Moving2(getClass().getResource(GOLDSHARKDOWN_PIC), "sharkdown", shark.x, shark.y, MARGIN,
					STEPS2, 1850, 900, shark_height, shark_width);
		if (left == true && level == 2)
			shark = new Moving2(getClass().getResource(GOLDSHARKLEFT_PIC), "sharkleft", shark.x, shark.y, MARGIN,
					STEPS2, 1850, 900, shark_width,shark_height);
		if (right == true && level == 2)
			shark = new Moving2(getClass().getResource(GOLDSHARKRIGHT_PIC), "sharkright", shark.x, shark.y, MARGIN,
					STEPS2, 1850, 900, shark_width, shark_height);
		if (right == true && up == true && level == 2)
			shark = new Moving2(getClass().getResource(GOLDSHARKRIGHTUP_PIC), "sharkrightup", shark.x, shark.y, MARGIN3,
					STEPS2, 1850, 900, shark_width2, shark_height2);
		if (right == true && down == true && level == 2)
			shark = new Moving2(getClass().getResource(GOLDSHARKRIGHTDOWN_PIC), "sharkrightdown", shark.x, shark.y,
					MARGIN3, STEPS2, 1850, 900, shark_width2, shark_height2);
		if (left == true && up == true && level == 2)
			shark = new Moving2(getClass().getResource(GOLDSHARKLEFTUP_PIC), "sharkleftup", shark.x, shark.y, MARGIN3,
					STEPS2, 1850, 900, shark_width2, shark_height2);
		if (left == true && down == true && level == 2)
			shark = new Moving2(getClass().getResource(GOLDSHARKLEFTDOWN_PIC), "sharkleftdown", shark.x, shark.y,
					MARGIN3, STEPS2, 1850, 900, shark_width2, shark_height2);

	}

	public void boostOn() {
		STEPS2 *= 2;
		gamePanel.requestFocus();
		gamePanel.setFocusable(true);
	}

	public void boostOff() {
		if(level==1)
		STEPS2 = 2;
		else
			STEPS2=3;
		gamePanel.requestFocus();
		gamePanel.setFocusable(true);
	}

	private void finishGame() {
		startMusic.stop();
		gameOverMusic.play();
		frame.setSize(WIN_WIDTH, WIN_HEIGHT);
		goClock.stop();
		goAnime.stop();
		goLevelup.stop();
		card.show(frame.getContentPane(), "n3");
		gamePanel.setFocusable(false);
		gameOverPanel.requestFocus();
		gameOverPanel.setFocusable(true);
		gameOverPanel.addKeyListener(new DirectionListener());
	}

	private void resetGame() {
		gameOverMusic.stop();
		x = 0;
		//y = 0;
		sub_make = 0;
		shark_width = 200;
		shark_height = 100;
		shark_width2 = 235;
		shark_height2 = 225;
		level = 1;
		startMusic.play();
		SCORE = 0;
		life = 200;
		shark = new Moving2(getClass().getResource(SHARKRIGHT_PIC), "sharkright", 300, 300, MARGIN, STEPS2,
				gamePanelWidth, gamePanelHeight, 130, 100);
		up = false;
		down = false;
		left = false;
		right = false;
	}

	public class AnimeListener implements ActionListener {
		int time = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			time++;
			if (shark.name == "sharkleftup") {
				for (Moving m : foodList) {
					if (m.collide(new Point(shark.x, shark.y))) {
						if (m.name == "fish")
							eatSound.play();
						if (m.name == "swimmer1") {
							eatSound.play();
							screamSound.play();
						}
						if (m.name == "swimmer2") {
							eatSound.play();
							screamSound2.play();
						}
						foodList.remove(m);
						SCORE += 100;
						shark_width += 10;
						shark_height += 5;
						shark_width2 += 11;
						shark_height2 += 7;
						blood = 1;
						blood_x = shark.x-100;
						blood_y = shark.y-100;
						if (life < 200)
							life += 10;
						return;
					}
				}
			}
			if (shark.name == "sharkleft") {
				for (Moving m : foodList) {
					if (m.collide(new Point(shark.x, shark.y + (shark.height / 2)))) {
						if (m.name == "fish")
							eatSound.play();
						if (m.name == "swimmer1") {
							eatSound.play();
							screamSound.play();
						}
						if (m.name == "swimmer2") {
							eatSound.play();
							screamSound2.play();
						}
						foodList.remove(m);
						SCORE += 100;
						shark_width += 10;
						shark_height += 5;
						shark_width2 += 11;
						shark_height2 += 7;
						blood = 1;
						blood_x = shark.x-100;
						blood_y = shark.y;
						if (life < 200)
							life += 10;
						return;
					}
				}
			}
			if (shark.name == "sharkleftdown") {
				for (Moving m : foodList) {
					if (m.collide(new Point(shark.x, shark.y + shark.height))) {
						if (m.name == "fish")
							eatSound.play();
						if (m.name == "swimmer1") {
							eatSound.play();
							screamSound.play();
						}
						if (m.name == "swimmer2") {
							eatSound.play();
							screamSound2.play();
						}
						foodList.remove(m);
						SCORE += 100;
						shark_width += 10;
						shark_height += 5;
						shark_width2 += 11;
						shark_height2 += 7;
						blood = 1;
						blood_x = shark.x-100;
						blood_y = shark.y +100;
						if (life < 200)
							life += 10;
						return;
					}
				}
			}
			if (shark.name == "sharkrightup") {
				for (Moving m : foodList) {
					if (m.collide(new Point(shark.x + shark.width, shark.y))) {
						if (m.name == "fish")
							eatSound.play();
						if (m.name == "swimmer1") {
							eatSound.play();
							screamSound.play();
						}
						if (m.name == "swimmer2") {
							eatSound.play();
							screamSound2.play();
						}
						foodList.remove(m);
						SCORE += 100;
						shark_width += 10;
						shark_height += 5;
						shark_width2 += 11;
						shark_height2 += 7;
						blood = 1;
						blood_x = shark.x + 100;
						blood_y = shark.y-100;
						if (life < 200)
							life += 10;
						return;
					}
				}
			}
			if (shark.name == "sharkright") {
				for (Moving m : foodList) {
					if (m.collide(new Point(shark.x + shark.width, shark.y + (shark.height / 2)))) {
						if (m.name == "fish")
							eatSound.play();
						if (m.name == "swimmer1") {
							eatSound.play();
							screamSound.play();
						}
						if (m.name == "swimmer2") {
							eatSound.play();
							screamSound2.play();
						}
						foodList.remove(m);
						SCORE += 100;
						shark_width += 10;
						shark_height += 5;
						shark_width2 += 11;
						shark_height2 += 7;
						blood = 1;
						blood_x = shark.x +100;
						blood_y = shark.y;
						if (life < 200)
							life += 10;
						return;
					}
				}
			}
			if (shark.name == "sharkrightdown") {
				for (Moving m : foodList) {
					if (m.collide(new Point(shark.x + shark.width, shark.y + shark.height))) {
						if (m.name == "fish")
							eatSound.play();
						if (m.name == "swimmer1") {
							eatSound.play();
							screamSound.play();
						}
						if (m.name == "swimmer2") {
							eatSound.play();
							screamSound2.play();
						}
						foodList.remove(m);
						SCORE += 100;
						shark_width += 10;
						shark_height += 5;
						shark_width2 += 11;
						shark_height2 += 7;
						blood = 1;
						blood_x = shark.x + 100;
						blood_y = shark.y + 100;
						if (life < 200)
							life += 10;
						return;
					}
				}
			}
			if (shark.name == "sharkup") {
				for (Moving m : foodList) {
					if (m.collide(new Point(shark.x + (shark.width / 2), shark.y))) {
						if (m.name == "fish")
							eatSound.play();
						if (m.name == "swimmer1") {
							eatSound.play();
							screamSound.play();
						}
						if (m.name == "swimmer2") {
							eatSound.play();
							screamSound2.play();
						}
						foodList.remove(m);
						SCORE += 100;
						shark_width += 10;
						shark_height += 5;
						shark_width2 += 11;
						shark_height2 += 7;
						blood = 1;
						blood_x = shark.x-100 ;
						blood_y = shark.y-100;
						if (life < 200)
							life += 10;
						return;
					}
				}
			}
			if (shark.name == "sharkdown") {
				for (Moving m : foodList) {
					if (m.collide(new Point(shark.x + (shark.width / 2), shark.y + shark.height))) {
						if (m.name == "fish")
							eatSound.play();
						if (m.name == "swimmer1") {
							eatSound.play();
							screamSound.play();
						}
						if (m.name == "swimmer2") {
							eatSound.play();
							screamSound2.play();
						}
						foodList.remove(m);
						SCORE += 100;
						shark_width += 10;
						shark_height += 5;
						shark_width2 += 11;
						shark_height2 += 7;
						blood = 1;
						blood_x = shark.x-100 ;
						blood_y = shark.y + 100;
						if (life < 200)
							life += 10;
						return;
					}
				}
			}

			if (time % 5 == 0) {
				if (shark.name == "sharkleftup") {
					for (Moving m : enemyList) {
						if (m.collide(new Point(shark.x, shark.y)))
							if (m.name == "missile") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
								break;
							}
						if (m.collide(new Point(shark.x, shark.y))) {
							if (m.name == "bomb") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
							}

							else if (m.name != "bomb" && m.size > shark.width / 2) {
								deadSound.play();
								life -= 50;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
							} else {
								eatSound.play();
								enemyList.remove(m);
								SCORE += 200;
								shark_width += 10;
								shark_height += 5;
								shark_width2 += 11;
								shark_height2 += 7;
								blood = 1;
								blood_x = shark.x;
								blood_y = shark.y;
								if (life < 200)
									life += 20;
							}
							return;
						}
					}

				}
				if (shark.name == "sharkleft") {
					for (Moving m : enemyList) {
						if (m.collide(new Point(shark.x, shark.y)))
							if (m.name == "missile") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
								break;
							}
						if (m.collide(new Point(shark.x, shark.y + (shark.height / 2)))) {
							if (m.name == "bomb") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
							//	y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
							/*	boom_x = shark.x;
								boom_y = shark.y;*/
							}

							else if (m.name != "bomb" && m.size > shark.width / 2) {
								deadSound.play();
								life -= 50;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
							} else {
								eatSound.play();
								enemyList.remove(m);
								SCORE += 200;
								shark_width += 10;
								shark_height += 5;
								shark_width2 += 11;
								shark_height += 7;
								blood = 1;
								blood_x = shark.x;
								blood_y = shark.y + (shark.height / 2);
								if (life < 200)
									life += 20;
							}
							return;
						}
					}

				}
				if (shark.name == "sharkleftdown") {
					for (Moving m : enemyList) {
						if (m.collide(new Point(shark.x, shark.y)))
							if (m.name == "missile") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
							//	y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
								break;
							}
						if (m.collide(new Point(shark.x, shark.y + shark.height))) {
							if (m.name == "bomb") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
							//	y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
							}

							else if (m.name != "bomb" && m.size > shark.width / 2) {
								deadSound.play();
								life -= 50;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
							} else {
								eatSound.play();
								enemyList.remove(m);
								SCORE += 200;
								shark_width += 10;
								shark_height += 5;
								shark_width2 += 11;
								shark_height2 += 7;
								blood = 1;
								blood_x = shark.x;
								blood_y = shark.y + shark.height;
								if (life < 200)
									life += 20;
							}
							return;
						}
					}

				}
				if (shark.name == "sharkrightup") {
					for (Moving m : enemyList) {
						if (m.collide(new Point(shark.x, shark.y)))
							if (m.name == "missile") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
								break;
							}
						if (m.collide(new Point(shark.x + shark.width, shark.y))) {
							if (m.name == "bomb") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
							}

							else if (m.name != "bomb" && m.size > shark.width / 2) {
								deadSound.play();
								life -= 50;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
							} else {
								eatSound.play();
								enemyList.remove(m);
								SCORE += 200;
								shark_width += 10;
								shark_height += 5;
								shark_width2 += 11;
								shark_height2 += 7;
								blood = 1;
								blood_x = shark.x + shark.width;
								blood_y = shark.y;
								if (life < 200)
									life += 20;
							}
							return;
						}
					}

				}
				if (shark.name == "sharkright") {
					for (Moving m : enemyList) {
						if (m.collide(new Point(shark.x, shark.y)))
							if (m.name == "missile") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
								break;
							}
						if (m.collide(new Point(shark.x + shark.width, shark.y + (shark.height / 2)))) {
							if (m.name == "bomb") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
							}

							else if (m.name != "bomb" && m.size > shark.width / 2) {
								deadSound.play();
								life -= 50;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
							} else {
								eatSound.play();
								enemyList.remove(m);
								SCORE += 200;
								shark_width += 10;
								shark_height += 5;
								shark_width2 += 11;
								shark_height2 += 7;
								blood = 1;
								blood_x = shark.x + shark.width;
								blood_y = shark.y + (shark.height / 2);
								if (life < 200)
									life += 20;
							}
							return;
						}
					}

				}
				if (shark.name == "sharkrightdown") {
					for (Moving m : enemyList) {
						if (m.collide(new Point(shark.x, shark.y)))
							if (m.name == "missile") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
								break;
							}
						if (m.collide(new Point(shark.x + shark.width, shark.y + shark.height))) {
							if (m.name == "bomb") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
							}

							else if (m.name != "bomb" && m.size > shark.width / 2) {
								deadSound.play();
								life -= 50;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
							} else {
								eatSound.play();
								enemyList.remove(m);
								SCORE += 200;
								shark_width += 10;
								shark_height += 5;
								shark_width2 += 11;
								shark_height2 += 7;
								blood = 1;
								blood_x = shark.x + shark.width;
								blood_y = shark.y + shark.height;
								if (life < 200)
									life += 20;
							}
							return;
						}
					}

				}
				if (shark.name == "sharkup") {
					for (Moving m : enemyList) {
						if (m.collide(new Point(shark.x, shark.y)))
							if (m.name == "missile") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
								break;
							}
						if (m.collide(new Point(shark.x + (shark.width / 2), shark.y))) {
							if (m.name == "bomb") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
							}

							else if (m.name != "bomb" && m.size > shark.width / 2) {
								deadSound.play();
								life -= 50;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
							} else {
								eatSound.play();
								enemyList.remove(m);
								SCORE += 200;
								shark_width += 10;
								shark_height += 5;
								shark_width2 += 11;
								shark_height2 += 7;
								blood = 1;
								blood_x = shark.x + (shark.width / 2);
								blood_y = shark.y;
								if (life < 200)
									life += 20;
							}
							return;
						}
					}

				}
				if (shark.name == "sharkdown") {
					for (Moving m : enemyList) {
						if (m.collide(new Point(shark.x, shark.y)))
							if (m.name == "missile") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
								break;
							}
						if (m.collide(new Point(shark.x + (shark.width / 2), shark.y + shark.height))) {
							if (m.name == "bomb") {
								bombSound.play();
								enemyList.remove(m);
								life -= 50;
								//y = 1;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
								/*boom_x = shark.x;
								boom_y = shark.y;*/
							}

							else if (m.name != "bomb" && m.size > shark.width / 2) {
								deadSound.play();
								life -= 50;
								shark_width -= 40;
								shark_height -= 20;
								shark_width2 -= 44;
								shark_height2 -= 32;
							} else {
								eatSound.play();
								enemyList.remove(m);
								SCORE += 200;
								shark_width += 10;
								shark_height2 += 5;
								shark_width2 += 11;
								shark_height2 += 7;
								blood = 1;
								blood_x = shark.x + (shark.width / 2);
								blood_y = shark.y + shark.height;
								if (life < 200)
									life += 20;
							}
							return;
						}
					}

				}

			}

			for (Moving m : enemyList) {
				m.move();
			}
			for (Moving m : foodList) {
				m.move();
			}

			if (life <= 0)
				finishGame();
			if (sub_make > 1) {
				sub_make = 1;
				goMissile.start();
			}

			scoreLabel.setText("점수 : " + SCORE + "점");
			frame.repaint();
		}
	}

	private class ClockListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			times2++;
			if (times2 % 5 == 0) {
				boostOff();
				goBoost.stop();

			}

		}
	}

	private class ClockListener3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (SCORE > 600) {
				time2++;
				x = 0;
				level = 2;
				STEPS2 = 3;
				life=200;
				levelUp.play();
				goLevelupPic.start();
				goLevelup.stop();
			}

		}

	}

	private class ClockListener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			time3++;
			if (time3 % 5 == 0) {
				time2 = 0;
				goLevelupPic.stop();
			}

		}

	}

	private class ClockListener5 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (sub_make > 0) {
				for (Moving m : enemyList) {
					if (m.name == "sub") {
						enemyList.add(new HorizontallyMoving(getClass().getResource(MISSILE_PIC), "missile", m.x,
								m.y + m.size, MARGIN, 300, STEPS + 20, 1850, 900));
						goMissile.stop();
						break;

					}

				}

			}

		}

	}

	private class ClockListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			times++;
			if (times % 2 == 0) {
				//y = 0;
				blood = 0;
			}

			if (times % FOOD_MAKING_TIME == 0) {
				for (int i = 0; i < 3; i++) {
					int rand = (int) (Math.random() * 23) + 1;
					switch (rand) {
					case 1:
						foodList.add(new DiagonallyMoving(getClass().getResource(BLOBFISH_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 2:
						foodList.add(new HorizontallyMoving(getClass().getResource(BLOBFISH_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 3:
						foodList.add(new VerticallyMoving(getClass().getResource(BLOBFISH_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 4:
						foodList.add(new DiagonallyMoving(getClass().getResource(FISH_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 5:
						foodList.add(new HorizontallyMoving(getClass().getResource(FISH_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 6:
						foodList.add(new VerticallyMoving(getClass().getResource(FISH_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 7:
						foodList.add(new DiagonallyMoving(getClass().getResource(SARDINE_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 8:
						foodList.add(new HorizontallyMoving(getClass().getResource(SARDINE_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 9:
						foodList.add(new VerticallyMoving(getClass().getResource(SARDINE_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 10:
						foodList.add(new DiagonallyMoving(getClass().getResource(TUNA_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 11:
						foodList.add(new HorizontallyMoving(getClass().getResource(TUNA_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 12:
						foodList.add(new VerticallyMoving(getClass().getResource(TUNA_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 13:
						foodList.add(new DiagonallyMoving(getClass().getResource(BARRACUDA_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 14:
						foodList.add(new HorizontallyMoving(getClass().getResource(BARRACUDA_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 15:
						foodList.add(new VerticallyMoving(getClass().getResource(BARRACUDA_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 16:
						foodList.add(new DiagonallyMoving(getClass().getResource(SAILFISH_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 17:
						foodList.add(new HorizontallyMoving(getClass().getResource(SAILFISH_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;

					case 18:
						foodList.add(new VerticallyMoving(getClass().getResource(SAILFISH_PIC), "fish", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 19:
						foodList.add(new DiagonallyMoving(getClass().getResource(SWIMMER_PIC), "swimmer1", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 20:
						foodList.add(new HorizontallyMoving(getClass().getResource(SWIMMER_PIC), "swimmer1", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 21:
						foodList.add(new VerticallyMoving(getClass().getResource(SWIMMER_PIC), "swimmer1", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 22:
						foodList.add(new VerticallyMoving(getClass().getResource(SWIMMER2_PIC), "swimmer2", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					case 23:
						foodList.add(new VerticallyMoving(getClass().getResource(SWIMMER2_PIC), "swimmer2", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));
						break;
					default:
						foodList.add(new VerticallyMoving(getClass().getResource(SWIMMER2_PIC), "swimmer2", MARGIN,
								(int) (Math.random() * 60) + 70, STEPS, 1850, 900));

					}

				}
			}

			// 시간이 일정시간 지나면 bigAttacker 출현/소멸 시킴
			if (times % ENEMY_MAKING_TIME == 0) {
				int rand = (int) (Math.random() * 12) + 1;
				switch (rand) {
				case 1:
					enemyList.add(new DiagonallyMoving(getClass().getResource(ENEMYSHARK_PIC), "enemy", MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					break;
				case 2:
					enemyList.add(new HorizontallyMoving(getClass().getResource(ENEMYSHARK_PIC), "enemy", MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					break;
				case 3:
					enemyList.add(new VerticallyMoving(getClass().getResource(ENEMYSHARK_PIC), "enemy", MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					break;
				case 4:
					enemyList.add(new DiagonallyMoving(getClass().getResource(MINE_PIC), "bomb", MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					break;
				case 5:
					enemyList.add(new HorizontallyMoving(getClass().getResource(MINE_PIC), "bomb", MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					break;

				case 6:
					enemyList.add(new VerticallyMoving(getClass().getResource(MEGALODON_PIC), "enemy", MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					break;
				case 7:
					enemyList.add(new DiagonallyMoving(getClass().getResource(SUB_PIC), "sub",
							(int) (Math.random() * 1850), (int) (Math.random() * 950), MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					sub_make++;
					break;
				case 8:
					enemyList.add(new HorizontallyMoving(getClass().getResource(SUB_PIC), "sub",
							(int) (Math.random() * 1850), (int) (Math.random() * 950), MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					sub_make++;
					break;
				case 9:
					enemyList.add(new VerticallyMoving(getClass().getResource(SUB_PIC), "sub",
							(int) (Math.random() * 1850), (int) (Math.random() * 950), MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					sub_make++;
					break;
				case 10:
					enemyList.add(new DiagonallyMoving(getClass().getResource(WHALE_PIC), "enemy", MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					break;
				case 11:
					enemyList.add(new HorizontallyMoving(getClass().getResource(WHALE_PIC), "enemy", MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));
					break;
				default:
					enemyList.add(new VerticallyMoving(getClass().getResource(WHALE_PIC), "enemy", MARGIN2,
							(int) (Math.random() * 200) + 100, STEPS, 1850, 900));

				}

			}
		}

	}

	class DirectionListener implements KeyListener {
		public void keyPressed(KeyEvent event) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				up = true;

				break;

			case KeyEvent.VK_DOWN:
				down = true;

				break;
			case KeyEvent.VK_LEFT:
				left = true;

				break;
			case KeyEvent.VK_RIGHT:
				right = true;

				break;
			}
			changeShark();
		}

		public void keyTyped(KeyEvent event) {
		}

		public void keyReleased(KeyEvent event) {
			switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				up = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			}

		}
	}

}
