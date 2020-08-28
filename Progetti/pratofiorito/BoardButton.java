package daFornire;/*
 * BoardButton.java
 *
 * NOTE: Remember to set the {@code package} directive here if you move
 * the class to a package different from the default.
 */

import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Representation of a <i>MineSweeper</i> board button.
 *
 * <p>
 *     Each {@code BoardButton} may or may not contain a mine,
 *     depending on the value of the <i>mine</i> property.
 *     When the game starts, the player can start toggling buttons
 *     in the board. If the toggled button contains a mine,
 *     then the player loses and the game ends, otherwise
 *     the button sets its text to the number of directly adjacent
 *     cells (including diagonals) containing a mine.
 *     The objective is to uncover all game cells that have no mine in them.
 * </p>
 */
public class BoardButton extends JToggleButton {
    private static final String MINE_LABEL = "\u2620";

    /* The number of adjacent cells containing a mine */
    private int adjacentMines = 0;

    /* Whether this cell has a mine */
    private boolean mine;

    /**
     * Creates a new {@code BoardButton} with default state (no mine).
     */
    public BoardButton() {
        this.mine = false;
        this.setModel(new OneTimeToggleButtonModel());
        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 28));
    }

    /**
     * Returns {@code true} is this button contains a mine.
     *
     * @return true if this button contains a mine.
     */
    public boolean hasMine() {
        return mine;
    }

    /**
     * Sets whether this button contains a mine.
     *
     * @param mine whether this button contains a mine.
     */
    public void setMine(boolean mine) {
        this.mine = mine;
        repaint();
    }

    /**
     * Returns the number of adjacent cells that contain a mine.
     *
     * @return the number of adjacent cells that contain a mine
     */
    public int getAdjacentMinesCount() {
        return adjacentMines;
    }

    /**
     * Sets the number of adjacent cells that contain a mine.
     *
     * @param adjacentMines the number of adjacent cells that contain a mine
     */
    public void setAdjacentMinesCount(int adjacentMines) {
        this.adjacentMines = adjacentMines;
        repaint();
    }

    /**
     * Reset the button to the initial state.
     * You <strong>must</strong> call this method and NOT {@code setSelected(false)}
     * if you want to reset the button to the unselected state.
     */
    public void reset() {
        boolean enabled = isEnabled();
        setMine(false);
        setAdjacentMinesCount(0);
        setModel(new OneTimeToggleButtonModel());
        setEnabled(enabled);
    }

    /**
     * Reveal the content of the cell.
     * This is equivalent of calling {@code setSelected(true)} on the button.
     */
    public void reveal() {
        setSelected(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(30, 50);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isSelected()) {
            setBackground(Color.LIGHT_GRAY);
            if (hasMine()) {
                setForeground(Color.BLACK);
                setText(MINE_LABEL);
            } else {
                switch (getAdjacentMinesCount()) {
                    case 0:
                        setForeground(Color.GREEN);
                        break;
                    case 1:
                    case 2:
                        setForeground(Color.BLUE);
                        break;
                    case 3:
                        setForeground(Color.RED);
                        break;
                    default:
                        setForeground(Color.BLACK);
                        break;
                }
                setText(getAdjacentMinesCount() + "");
            }
        } else {
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.BLACK);
            setText("");
        }
    }

    /* ******************************************
     * NESTED CLASSES
     ********************************************/

    /**
     * Subclass of {@link ToggleButtonModel} that implements
     * a one-time only selection behaviour for the button.
     */
    private static class OneTimeToggleButtonModel extends ToggleButtonModel {
        @Override
        public void setSelected(boolean b) {
            if (isSelected())
                return;
            super.setSelected(b);
        }
    }

}
