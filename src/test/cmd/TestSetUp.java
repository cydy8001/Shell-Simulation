package test.cmd;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmd.DirectoryList;
import cmd.List;
import driver.JShell;
import files.Directory;
import files.TxtFile;

public class TestSetUp {
  final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  final PrintStream originalOut = System.out;
  final PrintStream originalErr = System.err;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }


 
  @Before
  public void initialize() {
    DirectoryList.createDirectoryList(new Directory());
    // Adding the base file(s) that our system will start with
    DirectoryList.getCurrent().addFile(new TxtFile("file1", "File1 - Content"));
    DirectoryList.getCurrent().addFile(new TxtFile("file2", "File2 - Content"));
    DirectoryList.getCurrent().addFile(new TxtFile("file3", "File3 - Content"));

    // Adding a base directory
    Directory d1 = new Directory("d1", DirectoryList.getCurrent());
    Directory d2 = new Directory("d2", DirectoryList.getCurrent());
    // Adding d3 in d1
    Directory d3 = new Directory("d3", d1);
    // Adds files to that base directory
    d1.addFile(new TxtFile("file1.txt", d1, "File1"));
    d1.addFile(new TxtFile("file2.txt", d1, "File2"));
    d1.addFile(new TxtFile("file3.txt", d1, "File3"));
    d1.addFile(new TxtFile("file4.txt", d1, "File4"));
    // Adds files to d3
    d3.addFile(new TxtFile("d3file1", d3, "d3file1"));
    d3.addFile(new TxtFile("d3file2", d3, "d3file2"));
    DirectoryList.addDir(d1);
    DirectoryList.addDir(d2);
    DirectoryList.addDir(d3);

  }

}
