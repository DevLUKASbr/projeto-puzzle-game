import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoardGUI implements ActionListener {
    JFrame fr;
    JPanel mainPanel;
    JButton [][] button;
    int rows ;
    int cols ;
    JLabel [][] label;
    int [][] board;
    public BoardGUI() 
    {
        rows = 4;
        cols = 4;
        board = new int[rows][cols];
        initGUI();
    }
    public void initGUI()
    {
        fr = new JFrame("15 Puzzle Game");
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new GridLayout(4,4));
        button = new JButton[rows][cols];
        label  = new JLabel[rows][cols];
        //chamar o shuffleboard
        this.shuffleBoard();
        //definindo a imagem do botão
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                button[i][j] = new JButton();
                String text = i+","+j; 
                button[i][j].setText(text);
                button[i][j].setFont(new Font("TimesRoman",Font.PLAIN,0));
                button[i][j].addActionListener(this);
                
                int val = board[i][j];
                String fileName;
                if(val!=-1)
                {
                    fileName = "Pics/" + val + ".png";
                    label[i][j] = new JLabel(new ImageIcon(fileName), JLabel.CENTER);                
                    
                }
                else
                {
                    fileName = "Pics/" + 16 + ".png";
                    label[i][j] = new JLabel(new ImageIcon(fileName), JLabel.CENTER);                   
                }
                System.out.println(fileName);
                button[i][j].add(label[i][j]);
                button[i][j].setBorder(BorderFactory.createLineBorder(Color.black,2));
                button[i][j].setBackground(Color.white);
                mainPanel.add(button[i][j]);
            }
        }
        
        fr.add(mainPanel);
        fr.setVisible(true);
        fr.setSize(600,600);
      
        fr.setLocationRelativeTo(null); 
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void shuffleBoard()
    {
        Random rand = new Random();
        int [] array = new int[16];
        for(int i=0;i<16;i++)
        {
            array[i] = i+1;
        }
        array[15] = -1;
        
        for(int i=0;i<16;i++)
        {
            int index =  rand.nextInt(16);
          
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        
        int count = 0;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                board[i][j] = array[count];
                count = count + 1;
             
            }
            
        }
        
    }
    Boolean isWin()
    {
        int count = 1;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(board[i][j]!=count && board[i][j]!=-1)
                {
                    return false;
                }
                count = count + 1;
            }
        }
        return true;
    }
    public void displayWinMsg()
    {
         JFrame frame = new JFrame("Game Win");
         JLabel label = new JLabel("You Solve The Puzzle",JLabel.CENTER);
         label.setFont(new Font("TimesRoman",Font.BOLD,20));
         frame.add(label);
        frame.setLayout(new GridLayout(1,1));
        frame.setSize(300,300);
        frame.setBackground(Color.white);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        Boolean flag = isWin();
        if(flag==false)
        {
             String s = ae.getActionCommand().toString();
            int r = Integer.parseInt(s.split(",")[0]);
            int c = Integer.parseInt(s.split(",")[1]);
        
            if(board[r][c]!=-1)
            {
                if(r+1<rows && board[r+1][c]==-1)  
                {
                    label[r][c].setIcon(new ImageIcon(""));
                    String fileName = "Pics/" + board[r][c] + ".png";
                    label[r+1][c].setIcon(new ImageIcon(fileName));
                    int temp = board[r][c];
                  board[r][c] = board[r+1][c];
                 board[r+1][c] = temp;
                }
                else if(r-1>=0 && board[r-1][c]==-1) 
                {
                    label[r][c].setIcon(new ImageIcon(""));
                    String fileName = "Pics/" + board[r][c] + ".png";
                    label[r-1][c].setIcon(new ImageIcon(fileName));
                    int temp = board[r][c];
                    board[r][c] = board[r-1][c];
                    board[r-1][c] = temp;
                }
                else if(c+1<cols && board[r][c+1]==-1) 
                {
                    label[r][c].setIcon(new ImageIcon(""));
                    String fileName = "Pics/" + board[r][c] + ".png";
                    label[r][c+1].setIcon(new ImageIcon(fileName));
                    int temp = board[r][c];
                    board[r][c] = board[r][c+1];
                    board[r][c+1] = temp;
                }
                else if(c-1>=0 && board[r][c-1]==-1) 
                {
                    label[r][c].setIcon(new ImageIcon(""));
                    String fileName = "Pics/" + board[r][c] + ".png";
                    label[r][c-1].setIcon(new ImageIcon(fileName));
                    int temp = board[r][c];
                    board[r][c] = board[r][c-1];
                    board[r][c-1] = temp;
                }
            }  
            flag = isWin();
            if(flag==true)
            {
               displayWinMsg();
            }
        }
        
    }
}