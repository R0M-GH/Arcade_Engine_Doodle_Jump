import java.io.File;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Level extends World {
    Player p;
    boolean z = true;
    boolean add = false;
    NumberText height;
    NumberText coins;
    World w = this;
    boolean entered = false;
    boolean e = false;

    double maxXValue = 475;
    double maxYvalue = -10000;
    double randWidth;
    double y = 900;
    double x;
    double tempdy;
    double randWidthBroken;
    int randBlock;

    double prevXPos = 10;

    boolean paused = false;

    public Level() {

    }

    @Override
    public void act(long now) {
        if (this.isKeyPressed(KeyCode.LEFT) || this.isKeyPressed(KeyCode.A)) {
            p.move(-6, 0);
            p.setImage(new Image("file:img" + File.separator + "doodle.png"));
        } else if (this.isKeyPressed(KeyCode.RIGHT) || this.isKeyPressed(KeyCode.D)) {
            p.move(6, 0);
            p.setImage(new Image("file:img" + File.separator + "image.png"));
        }

        if (this.getObjects(Platform.class).size() < 35 && entered == true) {
            y = -2400;
            e = false;
            while (y > maxYvalue) {
                y -= 90;
                double loop = (int) (Math.random() * 9 + 1);
                randBlock = (int) (Math.random() * 4 + 1);
                randWidthBroken = Math.random() * 80 + 60;
                randWidth = Math.random() * 100 + 60;
                x = Math.random() * (maxXValue - randWidth);

                if (loop <= 4 || add == true) {
                    add = false;
                    if (randBlock == 1 && e == false) {
                        e = true;
                        BrokenPlatform m = new BrokenPlatform(x, y, randWidthBroken, 20);
                        this.add(m);
                        if (m != null && m.getIntersectingObjects(Platform.class).size() > 0) {
                            this.remove(m);
                        }
                    } else if (randBlock == 2 && e == false) {
                        FirePlatform m = new FirePlatform(x, y, randWidthBroken, 20);
                        this.add(m);
                        if (m != null && m.getIntersectingObjects(Platform.class).size() > 0) {
                            this.remove(m);
                        }
                        e = true;
                    } else {
                        e = false;
                        Platform m = new Platform(x, y, randWidthBroken, 20);
                        this.add(m);
                        if (m != null && m.getIntersectingObjects(Platform.class).size() > 0) {
                            this.remove(m);
                        }
                    }

                    int i = 1;
                    int numCoins = (int) (Math.random() * 2 + 1);
                    int gCoins = (int) (Math.random() * 3 + 1);

                    if (gCoins == 1) {
                        while (i <= numCoins) {
                            int xC = (int) (Math.random() * (maxXValue - 45));
                            if (this.getObjectsAt(xC - 22, y + 22.5, Coin.class).size() == 0
                                    && this.getObjectsAt(xC + 22, y + 22.5, Coin.class).size() == 0
                                    && this.getObjectsAt(xC, y + 22.5, Coin.class).size() == 0) {
                                Coin c = new Coin(xC, y + 22.5);
                                this.add(c);
                                if (c != null && c.getIntersectingObjects(Platform.class).size() > 0) {
                                    this.remove(c);
                                }
                                i++;
                            }
                        }
                    }

                } else if (loop == 5 || loop == 6) {
                    this.add(new MovingPlatform(x, y, randWidth, 20));
                } else {
                    add = true;
                    Monster m = new Monster(x, y);
                    this.add(m);
                    if (m != null && m.getIntersectingObjects(Platform.class).size() > 0) {
                        this.remove(m);
                    }
                }
            }
            entered = true;
        }
        if (p != null && p.isGameOver() && z == true && paused == false) {
            z = false;
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Game Over! Your score was " + getHeightScore() + " and the number of coins you collected was " + getCoins());
            a.show();
            ((BorderPane) w.getScene().getRoot()).setCenter(new TitleMenu());
        }
    }

    @Override
    public void onDimensionsInitialized() {
        BorderPane root = (BorderPane) w.getScene().getRoot();
        BackgroundImage bg = new BackgroundImage(new Image("file:img" + File.separator + "Background.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(bg);
        root.setBackground(background);

        height = new NumberText();
        height.setX(10);
        height.setY(35);
        this.getChildren().add(height);

        coins = new NumberText("Coins: ");

        coins.setX(10);
        coins.setY(this.getHeight() - 10);

        this.getChildren().add(coins);

        // TODO Auto-generated method stub
        this.add(new Platform(150, 310, 100, 20));

        while (y > maxYvalue) {
            y -= 90;

            randWidth = Math.random() * 100 + 60;
            x = Math.random() * (maxXValue - randWidth);

            randBlock = (int) (Math.random() * 3 + 1);
            randWidthBroken = Math.random() * 80 + 60;
            if (randBlock == 1 && e == false) {
                e = true;
                this.add(new BrokenPlatform(x, y, randWidthBroken, 20));
            } else if (randBlock == 2 && e == false) {
                this.add(new FirePlatform(x, y, randWidth, 20));
                e = true;
            } else {
                e = false;
                this.add(new Platform(x, y, randWidthBroken, 20));
            }

            int i = 1;
            int numCoins = (int) (Math.random() * 2 + 1);
            int gCoins = (int) (Math.random() * 4 + 1);

            if (gCoins == 1) {
                while (i <= numCoins) {
                    int xC = (int) (Math.random() * (maxXValue - 45));
                    if (this.getObjectsAt(xC - 22, y + 22.5, Coin.class).size() == 0
                            && this.getObjectsAt(xC + 22, y + 22.5, Coin.class).size() == 0
                            && this.getObjectsAt(xC, y + 22.5, Coin.class).size() == 0) {
                        this.add(new Coin(xC, y + 22.5));
                        i++;
                    }
                }
            }
        }
        entered = true;

        p = new Player();
        p.setX(150);
        p.setY(300);
        this.add(p);

        this.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                StackPane sptemp = ((StackPane) ((BorderPane) w.getScene().getRoot()).getCenter());

                if (event.getCode() == KeyCode.P) {
                    if (paused == false) {
                        // tempdy = w.getObjects(Player.class).get(0).getDy();
                        // w.getObjects(Player.class).get(0).setDy(0);
                        w.getObjects(Player.class).get(0).pauseGame();
                        ImageView pause = new ImageView(new Image("file:img" + File.separator + "pausemenu.png"));
                        pause.setFitWidth(((BorderPane) (w.getScene().getRoot())).getWidth());
                        pause.setFitHeight(((BorderPane) (w.getScene().getRoot())).getHeight());
                        paused = true;
                        sptemp.getChildren().add(pause);


                    

                    }
                }

                if (event.getCode() == KeyCode.ESCAPE) {
                    if (paused) {
                        sptemp.getChildren().remove(sptemp.getChildren().size() - 1);
                        if (sptemp.getChildren().size() == 1) {
                            w.getObjects(Player.class).get(0).setDy(tempdy);
                            paused = false;
                            w.getObjects(Player.class).get(0).unPause();
                        }
                    }
                }

                if (event.getCode() == KeyCode.R) {
                    if (paused && sptemp.getChildren().size() == 2) {
                        sptemp.getChildren().remove(1);
                        w.getObjects(Player.class).get(0).setDy(tempdy);
                        paused = false;
                    }
                    w.getObjects(Player.class).get(0).unPause();
                }

                if (event.getCode() == KeyCode.A) {
                    if (paused && sptemp.getChildren().size() == 2) {
                        ImageView about = new ImageView(
                                new Image("file:img" + File.separator + "about.png", 475, 2000, true, true));
                        ((StackPane) ((BorderPane) w.getScene().getRoot()).getCenter()).getChildren().add(about);
                    }
                }

                if (event.getCode() == KeyCode.Q) {
                    if (paused && sptemp.getChildren().size() == 2) {
                        ((BorderPane) w.getScene().getRoot()).setCenter(new TitleMenu());
                    }
                }
            }
        });
    }

    public int getHeightScore() {
        return height.getScore();
    }

    public void setHeightScore(int num) {
        height.setScore(num);
    }

    public int getCoins() {
        return coins.getScore();
    }

    public void setCoins(int num) {
        coins.setScore(num);
    }

    public void gameOver() {
        this.p.setGameOver(true);
    }
}
