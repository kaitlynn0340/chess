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

public class PawnChessComponent extends ChessComponent{
    /**
     * 这个类表示国际象棋里面的车
     */
        /**
         * 黑车和白车的图片，static使得其可以被所有车对象共享
         * <br>
         * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
         */
        private static Image PAWN_WHITE;
        private static Image PAWN_BLACK;

        /**
         * 车棋子对象自身的图片，是上面两种中的一种
         */
        private Image pawnImage;

        /**
         * 读取加载车棋子的图片
         *
         * @throws IOException
         */
        public void loadResource() throws IOException {
            if (PAWN_WHITE == null) {
                PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
            }

            if (PAWN_BLACK == null) {
                PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
            }
        }


        /**
         * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
         *
         * @param color 棋子颜色
         */

        private void initiatePawnImage(ChessColor color) {
            try {
                loadResource();
                if (color == ChessColor.WHITE) {
                    pawnImage = PAWN_WHITE;
                } else if (color == ChessColor.BLACK) {
                    pawnImage = PAWN_BLACK;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, String name) {
            super(chessboardPoint, location, color, listener, size, name);
            if (color == ChessColor.WHITE){name = "p";}
            if (color == ChessColor.BLACK){name = "P";}
            initiatePawnImage(color);
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
            List<ChessboardPoint> canMoveList = new ArrayList<>();
            ChessComponent movedChess;
            ChessComponent sourceChess = chessComponents[source.getX()][source.getY()];
            if (this.getChessColor() == ChessColor.BLACK) {

                if (source.getX()==1) {
                    if (source.getX() >= 0 && source.getX() <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                        if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                            ChessComponent frontChess = chessComponents[source.getX() + 1][source.getY()];

                            if (frontChess.getChessColor() != ChessColor.NONE) {

                                if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() - 1 >= 0 && source.getY() - 1 <= 7) {

                                    ChessComponent leftfrontChess = chessComponents[source.getX() + 1][source.getY() - 1];

                                    if (leftfrontChess.getChessColor() != sourceChess.getChessColor() && leftfrontChess.getChessColor() != ChessColor.NONE) {

                                        canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY() - 1));

                                    }
                                }

                                if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() + 1 >= 0 && source.getY() + 1 <= 7) {

                                    ChessComponent rightfrontChess = chessComponents[source.getX() + 1][source.getY() + 1];

                                    if (rightfrontChess.getChessColor() != sourceChess.getChessColor() && rightfrontChess.getChessColor() != ChessColor.NONE) {

                                        canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY() + 1));
                                    }
                                }

                            }
                            if (frontChess.getChessColor() == ChessColor.NONE) {

                                canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY()));
                                if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() - 1 >= 0 && source.getY() - 1 <= 7) {

                                    ChessComponent leftfrontChess = chessComponents[source.getX() + 1][source.getY() - 1];

                                    if (leftfrontChess.getChessColor() != sourceChess.getChessColor() && leftfrontChess.getChessColor() != ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY() - 1));
                                    }

                                }
                                if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() + 1 >= 0 && source.getY() + 1 <= 7) {

                                    ChessComponent rightfrontChess = chessComponents[source.getX() + 1][source.getY() + 1];

                                    if (rightfrontChess.getChessColor() != sourceChess.getChessColor() && rightfrontChess.getChessColor() != ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY() + 1));
                                    }

                                }
                                if (source.getX() + 2 >= 0 && source.getX() + 2 <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                                    ChessComponent front2Chess = chessComponents[source.getX() + 2][source.getY()];

                                    if (front2Chess.getChessColor() == ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() + 2, source.getY()));
                                    }

                                }

                            }
                        }
                    }
                }
                if (source.getX() !=1) {
                    if (source.getX() >= 0 && source.getX() <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                        if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                            ChessComponent frontChess = chessComponents[source.getX() + 1][source.getY()];

                            if (frontChess.getChessColor() != ChessColor.NONE) {

                                if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() - 1 >= 0 && source.getY() - 1 <= 7) {

                                    ChessComponent leftfrontChess = chessComponents[source.getX() + 1][source.getY() - 1];

                                    if (leftfrontChess.getChessColor() != sourceChess.getChessColor() && leftfrontChess.getChessColor() != ChessColor.NONE) {

                                        canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY() - 1));

                                    }
                                }

                                if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() + 1 >= 0 && source.getY() + 1 <= 7) {

                                    ChessComponent rightfrontChess = chessComponents[source.getX() + 1][source.getY() + 1];

                                    if (rightfrontChess.getChessColor() != sourceChess.getChessColor() && rightfrontChess.getChessColor() != ChessColor.NONE) {

                                        canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY() + 1));
                                    }
                                }

                            }  if (frontChess.getChessColor() == ChessColor.NONE) {

                                canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY()));
                                if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() - 1 >= 0 && source.getY() - 1 <= 7) {

                                    ChessComponent leftfrontChess = chessComponents[source.getX() + 1][source.getY() - 1];

                                    if (leftfrontChess.getChessColor() != sourceChess.getChessColor() && leftfrontChess.getChessColor() != ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY() - 1));
                                    }

                                }
                                if (source.getX() + 1 >= 0 && source.getX() + 1 <= 7 && source.getY() + 1 >= 0 && source.getY() + 1 <= 7) {

                                    ChessComponent rightfrontChess = chessComponents[source.getX() + 1][source.getY() + 1];

                                    if (rightfrontChess.getChessColor() != sourceChess.getChessColor() && rightfrontChess.getChessColor() != ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() + 1, source.getY() + 1));
                                    }

                                }


                            }
                        }
                    }
                }

            }
            if (this.getChessColor() == ChessColor.WHITE) {
                if (source.getX() ==6) {
                    if (source.getX() >= 0 && source.getX() <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                        if (source.getX() -1 >= 0 && source.getX() - 1 <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                            ChessComponent frontChess = chessComponents[source.getX() - 1][source.getY()];

                            if (frontChess.getChessColor() != ChessColor.NONE) {

                                if (source.getX() - 1 >= 0 && source.getX() - 1 <= 7 && source.getY() - 1 >= 0 && source.getY() - 1 <= 7) {

                                    ChessComponent leftfrontChess = chessComponents[source.getX() - 1][source.getY() - 1];

                                    if (leftfrontChess.getChessColor() != sourceChess.getChessColor() && leftfrontChess.getChessColor() != ChessColor.NONE) {

                                        canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY() - 1));

                                    }
                                }

                                if (source.getX() - 1 >= 0 && source.getX() - 1 <= 7 && source.getY() + 1 >= 0 && source.getY() + 1 <= 7) {

                                    ChessComponent rightfrontChess = chessComponents[source.getX() - 1][source.getY() + 1];

                                    if (rightfrontChess.getChessColor() != sourceChess.getChessColor() && rightfrontChess.getChessColor() != ChessColor.NONE) {

                                        canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY() + 1));
                                    }
                                }

                            } else if (frontChess.getChessColor() == ChessColor.NONE) {

                                canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY()));
                                if (source.getX() - 1 >= 0 && source.getX() - 1 <= 7 && source.getY() - 1 >= 0 && source.getY() - 1 <= 7) {

                                    ChessComponent leftfrontChess = chessComponents[source.getX() - 1][source.getY() - 1];

                                    if (leftfrontChess.getChessColor() != sourceChess.getChessColor() && leftfrontChess.getChessColor() != ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY() - 1));
                                    }

                                }
                                if (source.getX() - 1 >= 0 && source.getX() - 1 <= 7 && source.getY() + 1 >= 0 && source.getY() + 1 <= 7) {

                                    ChessComponent rightfrontChess = chessComponents[source.getX() - 1][source.getY() + 1];

                                    if (rightfrontChess.getChessColor() != sourceChess.getChessColor() && rightfrontChess.getChessColor() != ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY() + 1));
                                    }

                                }
                                if (source.getX() - 2 >= 0 && source.getX() - 2 <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                                    ChessComponent front2Chess = chessComponents[source.getX() - 2][source.getY()];

                                    if (front2Chess.getChessColor() == ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() - 2, source.getY()));
                                    }

                                }

                            }
                        }
                    }
                }
                if (source.getX() !=6) {
                    if (source.getX() >= 0 && source.getX() <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                        if (source.getX() -1 >= 0 && source.getX() - 1 <= 7 && source.getY() >= 0 && source.getY() <= 7) {

                            ChessComponent frontChess = chessComponents[source.getX() - 1][source.getY()];

                            if (frontChess.getChessColor() != ChessColor.NONE) {

                                if (source.getX() - 1 >= 0 && source.getX() - 1 <= 7 && source.getY() - 1 >= 0 && source.getY() - 1 <= 7) {

                                    ChessComponent leftfrontChess = chessComponents[source.getX() - 1][source.getY() - 1];

                                    if (leftfrontChess.getChessColor() != sourceChess.getChessColor() && leftfrontChess.getChessColor() != ChessColor.NONE) {

                                        canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY() - 1));

                                    }
                                }

                                if (source.getX() - 1 >= 0 && source.getX() - 1 <= 7 && source.getY() + 1 >= 0 && source.getY() + 1 <= 7) {

                                    ChessComponent rightfrontChess = chessComponents[source.getX() - 1][source.getY() + 1];

                                    if (rightfrontChess.getChessColor() != sourceChess.getChessColor() && rightfrontChess.getChessColor() != ChessColor.NONE) {

                                        canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY() + 1));
                                    }
                                }

                            } else if (frontChess.getChessColor() == ChessColor.NONE) {

                                canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY()));
                                if (source.getX() - 1 >= 0 && source.getX() - 1 <= 7 && source.getY() - 1 >= 0 && source.getY() - 1 <= 7) {

                                    ChessComponent leftfrontChess = chessComponents[source.getX() - 1][source.getY() - 1];

                                    if (leftfrontChess.getChessColor() != sourceChess.getChessColor() && leftfrontChess.getChessColor() != ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY() - 1));
                                    }

                                }
                                if (source.getX() - 1 >= 0 && source.getX() - 1 <= 7 && source.getY() + 1 >= 0 && source.getY() + 1 <= 7) {

                                    ChessComponent rightfrontChess = chessComponents[source.getX() - 1][source.getY() + 1];

                                    if (rightfrontChess.getChessColor() != sourceChess.getChessColor() && rightfrontChess.getChessColor() != ChessColor.NONE) {
                                        canMoveList.add(new ChessboardPoint(source.getX() - 1, source.getY() + 1));
                                    }
                                }

                            }
                        }
                    }
                }
            }
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
            g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
            g.setColor(Color.BLACK);
            if (isSelected()) { // Highlights the model if selected.
                g.setColor(Color.RED);
                g.drawOval(0, 0, getWidth() , getHeight());
            }
        }
    }
