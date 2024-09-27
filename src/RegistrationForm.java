import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegistrationForm extends JFrame implements ActionListener{

    private final JButton btn;
    private final JLabel title;
    private final JLabel name;
    private final JTextField tname;
    private final JLabel uname;
    private final JTextField user;
    private final JLabel mail;
    private final JTextField email;
    private final JLabel pass;
    private final JTextField tpass;
    private final JLabel sub;
    private final JLabel sname;
    private final JTextField usname;
    private final JLabel passw;
    private final JTextField tpassw;
    private final JButton sin;
    private final JLabel alt;
    public RegistrationForm(){
        setTitle("Registration");
        setBounds(50,20,550,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container container= getContentPane();
        container.setLayout(null);

        title= new JLabel("Sign up");
        title.setFont(new Font("Arial",Font.PLAIN,30));
        title.setSize(150,30);
        title.setLocation(200,25);
        container.add(title);

        name=new JLabel("Full Name :");
        name.setFont(new Font("Arial",Font.PLAIN,20));
        name.setSize(150,100);
        name.setLocation(30,65);
        container.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial",Font.PLAIN,20));
        tname.setSize(300,30);
        tname.setLocation(180,100);
        container.add(tname);

        uname=new JLabel("User Name :");
        uname.setFont(new Font("Arial",Font.PLAIN,20));
        uname.setSize(150,100);
        uname.setLocation(30,130);
        container.add(uname);

        user = new JTextField();
        user.setFont(new Font("Arial",Font.PLAIN,20));
        user.setSize(300,30);
        user.setLocation(180,165);
        container.add(user);


        mail=new JLabel("Email :");
        mail.setFont(new Font("Arial",Font.PLAIN,20));
        mail.setSize(150,100);
        mail.setLocation(30,195);
        container.add(mail);

        email = new JTextField();
        email.setFont(new Font("Arial",Font.PLAIN,20));
        email.setSize(300,30);
        email.setLocation(180,230);
        container.add(email);

        pass=new JLabel("Password :");
        pass.setFont(new Font("Arial",Font.PLAIN,20));
        pass.setSize(150,100);
        pass.setLocation(30,260);
        container.add(pass);

        tpass = new JTextField();
        tpass.setFont(new Font("Arial",Font.PLAIN,20));
        tpass.setSize(300,30);
        tpass.setLocation(180,300);
        container.add(tpass);


        btn= new JButton("Sign Up");
        btn.setFont(new Font("Arial",Font.PLAIN,20));
        btn.setSize(450,30);
        btn.setLocation(30,355);
        btn.addActionListener(this);
        container.add(btn);

        sub = new JLabel("Sign In");
        sub.setFont(new Font("Arial",Font.PLAIN,30));
        sub.setSize(150,30);
        sub.setLocation(200,420);
        container.add(sub);

        sname=new JLabel("User Name :");
        sname.setFont(new Font("Arial",Font.PLAIN,20));
        sname.setSize(150,100);
        sname.setLocation(30,440);
        container.add(sname);

        usname = new JTextField();
        usname.setFont(new Font("Arial",Font.PLAIN,20));
        usname.setSize(300,30);
        usname.setLocation(180,480);
        container.add(usname);

        passw=new JLabel("Password :");
        passw.setFont(new Font("Arial",Font.PLAIN,20));
        passw.setSize(150,100);
        passw.setLocation(30,505);
        container.add(passw);

        tpassw = new JTextField();
        tpassw.setFont(new Font("Arial",Font.PLAIN,20));
        tpassw.setSize(300,30);
        tpassw.setLocation(180,540);
        container.add(tpassw);

        sin = new JButton("Sign In");
        sin.setFont(new Font("Arial",Font.PLAIN,20));
        sin.setSize(450,30);
        sin.setLocation(30,600);
        sin.addActionListener(this);
        container.add(sin);

        alt = new JLabel("");
        alt.setFont(new Font("Arial",Font.BOLD,20));
        alt.setSize(500,40);
        alt.setLocation(50,650);
        container.add(alt);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==btn){
            if(!tname.getText().trim().isEmpty())
                if(!user.getText().trim().isEmpty())
                    if(!email.getText().trim().isEmpty())
                        if(!tpass.getText().trim().isEmpty()){
                            String namep = tname.getText();
                            String userp = user.getText();
                            String emailp = email.getText();
                            String passp = tpass.getText();

                            alt.setText("Successfully signed up");

                            usname.setEditable(false);
                            tpassw.setEditable(false);

                            String url = "jdbc:mysql://localhost:3306/registration";
                            String uname = "root";
                            String password = "";

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                            } catch (ClassNotFoundException exception) {
                                exception.printStackTrace();
                            }try {
                                Connection connection = DriverManager.getConnection(url,uname,password);
                                PreparedStatement preparedStmt = connection.prepareStatement("INSERT INTO `user` (`Fullname`, `Username`, `Email`, `Password`) VALUES (?, ?, ?, ?)");
                                preparedStmt.setString (1, namep);
                                preparedStmt.setString (2, userp);
                                preparedStmt.setString (3, emailp);
                                preparedStmt.setString (4, passp);

                                preparedStmt.execute();
                                connection.close();
                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                        }
                        else
                            alt.setText("Enter a password!");
                    else
                        alt.setText("Enter your email");
                else
                    alt.setText("Enter user name");
            else
                alt.setText("Enter your full name ");
        } else if (e.getSource()==sin) {
            if(!usname.getText().trim().isEmpty())
                if(!tpassw.getText().trim().isEmpty()) {
                    String namep = usname.getText();
                    String passp = tpassw.getText();


                    String url = "jdbc:mysql://localhost:3306/registration";
                    String uname = "root";
                    String password = "";

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException exception) {
                        exception.printStackTrace();
                    }
                    try {
                        Connection connection = DriverManager.getConnection(url, uname, password);
                        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
                        PreparedStatement prepared = connection.prepareStatement(query);
                        prepared.setString(1, namep);
                        prepared.setString(2, passp);

                        ResultSet resultSet = prepared.executeQuery();

                        if (resultSet.next()) {
                            // User exists, proceed with login
                            alt.setText("Sign in successful!");
                            System.out.println("logged in");
                        } else {
                            // User does not exist
                            alt.setText("You do not have an account.");
                        }

                        resultSet.close();
                        prepared.close();
                        connection.close();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                }
                else
                    alt.setText("Enter the password");
            else
                alt.setText("Enter the user name");
        }





    }

}


