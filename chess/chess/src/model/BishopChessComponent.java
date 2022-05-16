package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BishopChessComponent extends ChessComponent {
    /**
     * 这个类表示国际象棋里面的车
     */
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image bishopImage;

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateBishopImage(color);
    }

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
        }
    }

    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 车棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 车棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        List<ChessboardPoint> canMoveList = new ArrayList<>();
        ChessComponent movedChess;
        ChessComponent sourceChess = chessComponents[source.getX()][source.getY()];

        for (int i = 1; i < 8; i++) {
            if (source.getX() + i < 8 && source.getX() + i > -1 && source.getY() - i < 8 && source.getY() - i > -1) {
                ChessboardPoint toMove = new ChessboardPoint(source.getX() + i, source.getY() + i);
                movedChess = chessComponents[source.getX() + i][source.getY() + i];
                if (movedChess.getChessColor() == ChessColor.NONE) {
                    canMoveList.add(toMove);
                } else if (movedChess.getChessColor() == sourceChess.getChessColor()) {
                    break;
                } else {
                    canMoveList.add(toMove);
                    break;
                }
            }

        }
        for (int i = 1; i < 8; i++) {

                ChessboardPoint toMove = new ChessboardPoint(source.getX() - i, source.getY() - i);
            if (source.getX() - i <=7  && source.getX() - i >= 0 && source.getY() - i <= 7 && source.getY() - i >= 0) {
                movedChess = chessComponents[source.getX() - i][source.getY() - i];
                if (movedChess.getChessColor() == ChessColor.NONE) {
                    canMoveList.add(toMove);
                } else if (movedChess.getChessColor() == sourceChess.getChessColor()) {
                    break;
                } else {
                    canMoveList.add(toMove);
                    break;
                }

            }
        }
        for (int i = 1; i < 8; i++) {


            ChessboardPoint toMove = new ChessboardPoint(source.getX() + i, source.getY() - i);
            if (source.getX() + i < 8 && source.getX() + i > -1 && source.getY() - i < 8 && source.getY() - i > -1) {
                movedChess = chessComponents[source.getX() + i][source.getY() - i];
                if (movedChess.getChessColor() == ChessColor.NONE) {
                    canMoveList.add(toMove);
                } else if (movedChess.getChessColor() == sourceChess.getChessColor()) {
                    break;
                } else {
                    canMoveList.add(toMove);
                    break;
                }

            }

        }
        for (int i = 1; i < 8; i++) {

            ChessboardPoint toMove = new ChessboardPoint(source.getX() - i, source.getY() + i);
            if (source.getX() - i < 8 && source.getX() - i > -1 && source.getY() + i < 8 && source.getY() + i > -1) {
                movedChess = chessComponents[source.getX() - i][source.getY() + i];
                if (movedChess.getChessColor() == ChessColor.NONE) {
                    canMoveList.add(toMove);
                } else if (movedChess.getChessColor() == sourceChess.getChessColor()) {
                    break;
                } else {
                    canMoveList.add(toMove);
                    break;
                }

            }

        }

        return canMoveList.contains(destination);

    }

    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(bishopImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }
}
