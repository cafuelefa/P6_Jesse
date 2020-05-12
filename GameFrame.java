import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener
{
      

      private JLabel gameHeader = new JLabel("Would you like to play a game?");
      private JButton startButton = new JButton("Start Game");
      private JLabel gamePrompt = new JLabel();
      private JLabel gameHint = new JLabel();
      private JTextField input = new JTextField(6);
      private int magicNumber;
      private int guessCounter = 0;
      private int guess;
      private int lastGuess;
      
      public GameFrame()
      {
         super("Cool Number Game");
         setSize(450,150);
         setLayout(new FlowLayout());
         add(gameHeader);
         add(startButton);
         getContentPane().setBackground(Color.WHITE);
         input.addActionListener(this);
         startButton.addActionListener(this);      
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
      }//end GameFrame constructor

      public void actionPerformed(ActionEvent e)
      {
         Object source = e.getSource();
         
         if(source instanceof JButton)
         {
            magicNumber = (int) (Math.random()*1000);
            guessCounter = 0;
            this.getContentPane().setBackground(Color.WHITE);
            System.out.println("Debug statement: Magic number is " + magicNumber);
            this.remove(startButton);
            gameHeader.setText("I have a number between 1 and 1000, can you guess my number?" );
            gamePrompt.setText("Please enter a number for your first guess and then hit Enter." );
            this.add(gamePrompt);
            this.add(input);
            
         }//starts game and constructs gameplay frame
         
         if(source instanceof JTextField)//captures input from text fields (guess input)
         {
            guess = Integer.parseInt(input.getText());
            input.setText("");
            if (guess == magicNumber)
            {
               this.getContentPane().setBackground(Color.GREEN);
               this.remove(input);
               this.remove(gameHint);
               
               gameHeader.setText("Great job! " + guess + " was my magic number! Total guesses was " + (guessCounter+1));
               gamePrompt.setText("Would you like to play again?" );
               
               this.add(startButton);
               startButton.setText("Start New Game");
               
            }// first if statement to catch correct answers
            else if (guessCounter == 0)
            {
               this.getContentPane().setBackground(Color.RED);
               gameHeader.setText("Sorry, " + guess + " was not my magic number." );
               
               if ( guess < magicNumber )
               {
                  gameHint.setText( "Your guess was too low." );
               }
               else if ( guess > magicNumber )
               {
                  gameHint.setText( "Your guess was too high." );
               }
               
               this.remove(gamePrompt);
               this.remove(input);               
               this.add(gameHint);
               this.add(gamePrompt);
               this.add(input);
               
               gamePrompt.setText("Enter a number between 1 and 1000 for guess number " + (guessCounter+2) + " and press enter." );
            }// else if statement to catch first guess
            else
            {
               if( Math.abs(magicNumber-guess) > Math.abs(magicNumber-lastGuess) )  /* cold/warm is determined */
               {                                                                    /*   by absolute distance  */
                  this.getContentPane().setBackground(Color.BLUE);                  /*  from the magic number  */
                  
                  if ( guess < magicNumber )
                  {
                     gameHint.setText( "Your guess was too low." );
                  }
                  else if ( guess > magicNumber )
                  {
                     gameHint.setText( "Your guess was too high." );
                  }             
                       
                  gameHeader.setText("Sorry, " + guess + " was not my magic number. You're getting colder." );
                  gamePrompt.setText("Enter a number between 1 and 1000 for guess number " + (guessCounter+2) + " and press enter." );
               }//getting colder if
               if( Math.abs(magicNumber-guess) < Math.abs(magicNumber-lastGuess) )  /* cold/warm is determined */
               {                                                                    /*   by absolute distance  */
                  this.getContentPane().setBackground(Color.RED);                   /*  from the magic number  */
                  
                  if ( guess < magicNumber )
                  {
                     gameHint.setText( "Your guess was too low." );
                  }
                  else if ( guess > magicNumber )
                  {
                     gameHint.setText( "Your guess was too high." );
                  }                  
                  
                  gameHeader.setText("Sorry, " + guess + " was not my magic number. You're getting warmer!");
                  gamePrompt.setText("Enter a number between 1 and 1000 for guess number " + (guessCounter+2) + " and press enter.");
               }//getting warmer if
            }
            guessCounter++;
            lastGuess = guess;
         }
      }//end method actionPerformed
}//end class GameFrame