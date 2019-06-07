package csuedSource;

import java.io.*;

public class Help
/****************************************************************************
*  AUTH:  Truly, Yours                    DATE:  Oct. 1999                  *
*  DEPT:  Computer Science, CS-200        ORG.:  Colorado State University  *
*****************************************************************************
*                                                                           *
*  FILE:  Help.java                                                         *
*                                                                           *
*  DESC:  Contains the member functions for the Help Class.        .        *
*                                                                           *
****************************************************************************/
{

  //***************************************************************************
  public static void General()
  {
	  System.out.println("���� ���...");
  }

  //***************************************************************************
  public static void Command(char cmd)
  {
	  if(cmd=='Q')
		  System.out.println("Q�� ���� ���� ���...");
	  else if (cmd=='L')
		  System.out.println("L�� ���� ���� ���...");
	  //... ���Ƽ� ����.
  }

} // EndClass Help

