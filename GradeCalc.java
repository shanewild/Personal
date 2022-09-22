// Course: CS3642-WO1-FA-2022
// Student name: Shane Wild
// Student ID: 000924042
// Assignment #:1
// Due Date: 9/19
// Signature: Shane Wild
// Score: ______________
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GradeCalc implements ActionListener {
    //all the initialization of the GUI elements
    private static JLabel instructions = new JLabel("Enter all of your grades for the respective category out of 100", SwingConstants.CENTER);
    private static JFrame frame=new JFrame();
    private static JPanel panel=new JPanel();
    private static JLabel interactLabel=new JLabel("Interaction");
    private static JLabel assignment1Label=new JLabel("Assignment 1");
    private static JLabel assignment2Label=new JLabel("Assignment 2");
    private static JLabel assignment3Label=new JLabel("Assignment 3");
    private static JLabel test1Label=new JLabel("Test 1");
    private static JLabel test2Label=new JLabel("Test 2");
    private static JLabel test3Label=new JLabel("Test 3");
    private static JLabel researchProjectLabel=new JLabel("Research Project");
    private static JLabel numberGradeLabel=new JLabel();
    private static JLabel letterGradeLabel=new JLabel();

    private static JTextField interactTF=new JTextField();
    private static JTextField assignment1TF=new JTextField();
    private static JTextField assignment2TF=new JTextField();
    private static JTextField assignment3TF=new JTextField();
    private static JTextField test1TF=new JTextField();
    private static JTextField test2TF=new JTextField();
    private static JTextField test3TF=new JTextField();
    /*Interaction 5%
    Assignments 7.5% each
    Test1 10%
    test2 and 3 15%
    Research Proj 30%*/
    private final double INTERACT_WEIGHT=.05,T1_WEIGHT=0.1,T2_WEIGHT=0.15, T3_WEIGHT=0.15, AS1_WEIGHT=0.08333,AS2_WEIGHT=0.08333,AS3_WEIGHT=0.08333,PROJECT_WEIGHT=0.3;

    JTextField researchProjectTF=new JTextField();
    //default constructor which adds the GUI elements to the panel.
    public GradeCalc(){
        //button will call actionPerformed method which calls calculateGrades()
        JButton button1=new JButton("Calculate");
        button1.addActionListener(this);

        frame.setSize(500,600);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Grade Calc");
        frame.setVisible(true);

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(instructions);
        panel.add(interactLabel);
        panel.add(interactTF);
        panel.add(assignment1Label);
        panel.add(assignment1TF);
        panel.add(assignment2Label);
        panel.add(assignment2TF);
        panel.add(assignment3Label);
        panel.add(assignment3TF);
        panel.add(test1Label);
        panel.add(test1TF);
        panel.add(test2Label);
        panel.add(test2TF);
        panel.add(test3Label);
        panel.add(test3TF);
        panel.add(researchProjectLabel);
        panel.add(researchProjectTF);
        panel.add(button1);
        panel.add(numberGradeLabel);
        panel.add(letterGradeLabel);

    }
    //main method which creates the GUI through the default constructor.
    public static void main(String[] Args){
        new GradeCalc();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        calculateGrades();
    }
    public void calculateGrades(){
        try {
            //takes all the values from the txtFields and converts them to int
            int interact = Integer.parseInt(interactTF.getText());
            int as1 = Integer.parseInt(assignment1TF.getText());
            int as2 = Integer.parseInt(assignment2TF.getText());
            int as3 = Integer.parseInt(assignment3TF.getText());
            int t1 = Integer.parseInt(test1TF.getText());
            int t2 = Integer.parseInt(test2TF.getText());
            int t3 = Integer.parseInt(test3TF.getText());
            int rProj=Integer.parseInt(researchProjectTF.getText());
            if (rProj>100||rProj<0||as1>100||as1<0||as2>100||as2<0||as3>100||as3<0||t1>100||t1<0||t2>100||t2<0||t3>100||t3<0||rProj>100||rProj<0){
                throw new Exception("One of your grades is over 100 or under 0");
            }
            //calls calculateNumberGrade, uses that variable to call our agent, agentCalculateLetterGrade.
            double numberGrade=calculateNumberGrade(interact,as1,as2,as3,t1,t2,t3,rProj);
            numberGradeLabel.setText("Number Grade: " +numberGrade+"%");
            letterGradeLabel.setText(String.valueOf(agentCalculateLetterGrade(numberGrade)));

        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception, "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    //this method multiplies the grade and times them by their weight to calculate the grade
    public double calculateNumberGrade(int interact, int as1, int as2, int as3, int t1, int t2, int t3, int rProj){
        double totalVal=(interact*INTERACT_WEIGHT)+(as1*AS1_WEIGHT)+(as2*AS2_WEIGHT)+(as3*AS3_WEIGHT)+(t1*T1_WEIGHT)+(t2*T2_WEIGHT)+(t3*T3_WEIGHT)+(rProj*PROJECT_WEIGHT);

        return (double)Math.round(totalVal*100)/100;
    }
    //this is our simple agent which percepts the numberGrade and sends back a letter grade.
    public char agentCalculateLetterGrade(double numberGrade){
    if (numberGrade>89.5){
        return 'A';
    }
    else if(numberGrade>79.5){
        return 'B';
    }
    else if(numberGrade>69.5){
        return 'C';
    }
    else if (numberGrade>59.5){
        return 'D';
    }
    else return 'F';
    }
}
