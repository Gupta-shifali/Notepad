/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynotepad;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class MyNotepad implements ActionListener {
	JFrame f;
	JMenuBar mbar;
	JTextArea ta;
	JMenu file, edit, format, view, help;
	JMenuItem fNew, open, save, saveas, pgset, print, exit;
	JMenuItem undo, cut, copy, paste, delete, find, findnxt, selectall, timedate;
	JMenuItem wordwrap, font, upcase, lcase, toggle;
	JMenuItem statusbar;
	JMenuItem viewhelp, about;
        
        final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        int ind = 0;
        String wholeText, findStr;
        
	public MyNotepad()
	{
		f = new JFrame("Notepad");
		mbar = new JMenuBar();
		f.setJMenuBar(mbar);
		ta = new JTextArea();	
		f.add(ta);
		
		file = new JMenu("File");		
		file.setMnemonic(KeyEvent.VK_F);

		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);

		format = new JMenu("Format");
		format.setMnemonic(KeyEvent.VK_O);

		view = new JMenu("View");
		view.setMnemonic(KeyEvent.VK_V);

		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);

		fNew = new JMenuItem("New");
		fNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		fNew.addActionListener(this);

		open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.addActionListener(this);

		save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(this);

		saveas = new JMenuItem("SaveAs");
		saveas.addActionListener(this);

		pgset = new JMenuItem("Page Setup...");
		pgset.addActionListener(this);

		print = new JMenuItem("Print...");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		print.addActionListener(this);

		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		
		undo = new JMenuItem("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		undo.addActionListener(this);

		cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.addActionListener(this);

		copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copy.addActionListener(this);

		paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		paste.addActionListener(this);

		delete = new JMenuItem("Delete");
		delete.addActionListener(this);

		find = new JMenuItem("Find...");
		find.addActionListener(this);

		findnxt = new JMenuItem("Find Next");
		findnxt.addActionListener(this);

		selectall = new JMenuItem("Select All");
		selectall.addActionListener(this);
		
		timedate = new JMenuItem("Time/Date");
		timedate.addActionListener(this);

		wordwrap = new JMenuItem("Word Wrap");
		wordwrap.addActionListener(this);
                
		font = new JMenuItem("Font...");
		font.addActionListener(this);
                
                upcase = new JMenuItem("Upper Case");
                upcase.addActionListener(this);
                
                lcase = new JMenuItem("Lower Case");
                lcase.addActionListener(this);

		toggle = new JMenuItem("Toggle");
		toggle.addActionListener(this);

		statusbar = new JMenuItem("Status Bar");
		statusbar.addActionListener(this);

		viewhelp = new JMenuItem("View Help");
		viewhelp.addActionListener(this);
		
		about = new JMenuItem("About");
		about.addActionListener(this);
		
		file.add(fNew);
		file.add(open);
		file.add(save);
		file.add(saveas);
		file.addSeparator();
		file.add(pgset);
		file.add(print);
		file.addSeparator();
		file.add(exit);
		edit.add(undo);
		edit.addSeparator();
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		edit.addSeparator();
		edit.add(find);
		edit.add(findnxt);
		edit.addSeparator();
		edit.add(selectall);
		edit.add(timedate);
		format.add(wordwrap);
		format.add(upcase);
                format.add(lcase);
                format.add(toggle);
                format.add(font);
		view.add(statusbar);
		help.add(viewhelp);
		help.addSeparator();
		help.add(about);

		mbar.add(file);
		mbar.add(edit);
		mbar.add(format);
		mbar.add(view);
		mbar.add(help);
		f.setBounds(100,100,600,600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String args[])
	{
		new MyNotepad();
	}
	public void actionPerformed(ActionEvent ae)
	{
		JMenuItem mi = (JMenuItem)ae.getSource();
		try{
			if(mi == fNew){
				ta.setText("");
			}
			else if(mi == open){
				FileDialog fd = new FileDialog(f, "OPEN", FileDialog.LOAD);
                                fd.setVisible(true);
                                File file = new File(fd.getDirectory() + "\\" + fd.getFile());
                                Reader r = new BufferedReader(new FileReader(file));
                                char ch[] = new char[(int)file.length()]; 
                                r.read(ch, 0, ch.length);
                                String s = new String(ch);
                                ta.setText(s);
                                r.close();
                                f.setTitle(fd.getFile() + "- Notepad");
			}
			else if(mi == save){
				FileDialog fds = new FileDialog(f, "SAVE", FileDialog.SAVE);
                                fds.setVisible(true);
                                Writer w = new FileWriter(fds.getDirectory() + "\\" + fds.getFile());
                                String str = ta.getText();
                                w.write(str);
                                w.close();
                                f.setTitle(fds.getFile() + "- Notepad");
			}
			else if(mi == saveas){
				ta.setText("saveas is clicked");
			}
			else if(mi == pgset){
				ta.setText("pgset is clicked");
			}
			else if(mi == print){
				ta.setText("print is clicked");
			}
			else if(mi == exit){
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			else if(mi == undo){
				ta.setText("undo is clicked");
			}
			else if(mi == cut){
				String selection = ta.getSelectedText();
                                StringSelection data = new StringSelection(selection);
                                clip.setContents(data, null);
                                ta.replaceRange("", ta.getSelectionStart(), ta.getSelectionEnd());
			}
			else if(mi == copy){
				String selection = ta.getSelectedText();
                                StringSelection data = new StringSelection(selection);
                                clip.setContents(data, null);
			}
			else if(mi == paste){
                                Transferable clipData = clip.getContents(clip);
				try{
                                    if(clipData.isDataFlavorSupported(DataFlavor.stringFlavor)){
                                        String s = (String)(clipData.getTransferData(DataFlavor.stringFlavor));
                                        ta.replaceSelection(s);
                                    }
                                }
                                catch(Exception e){
                                    System.out.println(e.getMessage());
                                }
			}
                        else if(mi == delete){
				String selection = ta.getSelectedText();
                                StringSelection data = new StringSelection(selection);
                                clip.setContents(data, null);
                                ta.replaceRange("", ta.getSelectionStart(), ta.getSelectionEnd());
			}
			else if(mi == find){
				wholeText = ta.getText();
                                findStr = JOptionPane.showInputDialog(f, "Find What", "Find Title", JOptionPane.INFORMATION_MESSAGE);
                                ind = wholeText.indexOf(findStr, 0);
                                if(ind>=0){
                                    ta.setCaretPosition(ind);
                                    ta.setSelectionStart(ind);
                                    int a = ind + findStr.length();
                                    ta.setSelectionEnd(a);
                                }
			}
			else if(mi == findnxt){
				ta.setText("findnxt is clicked");
			}
			else if(mi == selectall){
				ta.setText("selectall is clicked");
			}
			else if(mi == timedate){
				ta.setText("timedate is clicked");
			}
			else if(mi == wordwrap){
				ta.setText("wordwrap is clicked");
			}
                        else if(mi == upcase){
				wholeText = ta.getText().toUpperCase();
                                ta.setText(wholeText);
			}
                        else if(mi == lcase){
				wholeText = ta.getText().toLowerCase();
                                ta.setText(wholeText);
			}
                        else if(mi == toggle){
				wholeText = ta.getText();
                                String result = "";
                                char currentChar;
                                for(int i=0; i<wholeText.length();i++){
                                    currentChar = wholeText.charAt(i);
                                    if(Character.isUpperCase(currentChar)){
                                        char currentCharToLowerCase = Character.toLowerCase(currentChar);
                                        result = result + currentCharToLowerCase;
                                    }else {
                                        char currentCharToUpperCase = Character.toUpperCase(currentChar);
                                        result = result + currentCharToUpperCase;
                                    }
                                }
                                ta.setText(result);
			}
			else if(mi == font){
				ta.setText("font is clicked");
			}
			else if(mi == statusbar){
				ta.setText("statusbar is clicked");
			}
			else if(mi == help){
				ta.setText("help is clicked");
			}
			else if(mi == about){
				ta.setText("about is clicked");
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

    private boolean typeof(String wholeText) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
