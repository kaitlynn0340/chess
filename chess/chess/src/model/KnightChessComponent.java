package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KnightChessComponent extends ChessComponent{
    /**
     * 这个类表示国际象棋里面的车
     */
        /**
         * 黑车和白车的图片，static使得其可以被所有车对象共享
         * <br>
         * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
         */
        private static Image KNIGHT_WHITE;
        private static Image KNIGHT_BLACK;

        /**
         * 车棋子对象自身的图片，是上面两种中的一种
         */
        private Image knightImage;

        /**
         * 读取加载车棋子的图片
         *
         * @throws IOException
         */
        public void loadResource() throws IOException {
            if (KNIGHT_WHITE == null) {
                KNIGHT_WHITE = ImageIO.read(new File("./images/knight-white.png"));
            }

            if (KNIGHT_BLACK == null) {
                KNIGHT_BLACK = ImageIO.read(new File("./images/knight-black.png"));
            }
        }


        /**
         * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
         *
         * @param color 棋子颜色
         */

        private void initiateKnightImage(ChessColor color) {
            try {
                loadResource();
                if (color == ChessColor.WHITE) {
                    knightImage = KNIGHT_WHITE;
                } else if (color == ChessColor.BLACK) {
                    knightImage = KNIGHT_BLACK;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, String name) {
            super(chessboardPoint, location, color, listener, size, name);
            if (color == ChessColor.WHITE){name = "N";}
            if (color == ChessColor.BLACK){name = "n";}
            initiateKnightImage(color);
        }
    public static ChessboardPoint getKing(ChessComponent[][]chessComponents,ChessColor color) {
        if (color == ChessColor.WHITE) {
            for (int i = 0; i<8 ; i++){
                for (int j = 0; j<8; j++){
                    if (chessComponents[i][j].getChessColor()==ChessColor.BLACK && Objects.equals(chessComponents[i][j].getName(), "K"))
                    {return chessComponents[i][j].getChessboardPoint();}
                }
            }return null;

        }
        else if (color == ChessColor.BLACK) {
            for (int i = 0; i<8 ; i++){
                for (int j = 0; j<8; j++){
                    if (chessComponents[i][j].getChessColor()==ChessColor.WHITE && Objects.equals(chessComponents[i][j].getName(), "k"))
                    {return chessComponents[i][j].getChessboardPoint();}
                }
            }return null;

        }else {return null;}
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
            ChessComponent movedChess;
            ChessComponent sourceChess = chessComponents[source.getX()][source.getY()];
            List<ChessboardPoint> canMoveList = new ArrayList<>();
            ChessboardPoint toMove = new ChessboardPoint(source.getX()-2,source.getY()+1);

            if (toMove.getX()<8 && toMove.getX()>-1 && toMove.getY() <8 && toMove.getY()>-1 ){
                movedChess = chessComponents[source.getX()-2][source.getY()+1];
                if (movedChess.getChessColor() != sourceChess.getChessColor()){canMoveList.add(toMove);}}

            toMove = new ChessboardPoint(source.getX()-2,source.getY()-1);
            if (toMove.getX()<8 && toMove.getX()>-1 && toMove.getY() <8 && toMove.getY()>-1 ){
                movedChess = chessComponents[source.getX()-2][source.getY()-1];
                if (movedChess.getChessColor() != sourceChess.getChessColor()){canMoveList.add(toMove);}}

            toMove = new ChessboardPoint(source.getX()+2,source.getY()+1);
            if (toMove.getX()<8 && toMove.getX()>-1 && toMove.getY() <8 && toMove.getY()>-1 ){
                movedChess = chessComponents[source.getX()+2][source.getY()+1];
                if (movedChess.getChessColor() != sourceChess.getChessColor()){canMoveList.add(toMove);}}

            toMove = new ChessboardPoint(source.getX()+2,source.getY()-1);
            if (toMove.getX()<8 && toMove.getX()>-1 && toMove.getY() <8 && toMove.getY()>-1 ){
                movedChess = chessComponents[source.getX()+2][source.getY()-1];
                if (movedChess.getChessColor() != sourceChess.getChessColor()){canMoveList.add(toMove);}}

            toMove = new ChessboardPoint(source.getX()-1,source.getY()+2);
            if (toMove.getX()<8 && toMove.getX()>-1 && toMove.getY() <8 && toMove.getY()>-1 ){
                movedChess = chessComponents[source.getX()-1][source.getY()+2];
                if (movedChess.getChessColor() != sourceChess.getChessColor()){canMoveList.add(toMove);}}

            toMove = new ChessboardPoint(source.getX()+1,source.getY()+2);
            if (toMove.getX()<8 && toMove.getX()>-1 && toMove.getY() <8 && toMove.getY()>-1 ){
                movedChess = chessComponents[source.getX()+1][source.getY()+2];
                if (movedChess.getChessColor() != sourceChess.getChessColor()){canMoveList.add(toMove);}}

            toMove = new ChessboardPoint(source.getX()-1,source.getY()-2);
            if (toMove.getX()<8 && toMove.getX()>-1 && toMove.getY() <8 && toMove.getY()>-1 ){
                movedChess = chessComponents[source.getX()-1][source.getY()-2];
                if (movedChess.getChessColor() != sourceChess.getChessColor()){canMoveList.add(toMove);}}

            toMove = new ChessboardPoint(source.getX()+1,source.getY()-2);
            if (toMove.getX()<8 && toMove.getX()>-1 && toMove.getY() <8 && toMove.getY()>-1 ){
                movedChess = chessComponents[source.getX()+1][source.getY()-2];
                if (movedChess.getChessColor() != sourceChess.getChessColor()){canMoveList.add(toMove);}}
            if (canMoveList.contains(getKing(chessComponents, sourceChess.getChessColor()))) {
                System.out.println("LOSE");
                String loser = "";
                if (sourceChess.getChessColor() == ChessColor.BLACK){loser="WHITE";}
                if (sourceChess.getChessColor() == ChessColor.WHITE){loser="BLACK";}
                JOptionPane.showMessageDialog(null,sourceChess.getChessColor()+" win! "+" the "+ loser +" player lose this game ","who lose",JOptionPane.ERROR_MESSAGE);
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
            g.drawImage(knightImage, 0, 0, getWidth() , getHeight(), this);
            g.setColor(Color.BLACK);
            if (isSelected()) { // Highlights the model if selected.
                g.setColor(Color.RED);
                g.drawOval(0, 0, getWidth() , getHeight());
            }
        }
    }
