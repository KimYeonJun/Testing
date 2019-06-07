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
	  System.out.println("설명서 출력...");
  }

  //***************************************************************************
  public static void Command(char cmd)
  {
	  if(cmd=='Q')
		  System.out.println("Q에 대한 설명 출력...");
	  else if (cmd=='L')
		  System.out.println("L에 대한 설명 출력...");
	  //... 많아서 생략.
  }

} // EndClass Help

