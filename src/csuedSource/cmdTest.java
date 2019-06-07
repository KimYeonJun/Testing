package csuedSource;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class cmdTest extends TestCase {
	
	//커밋 testing
	//asdf
	Cmd_Driver cmd;
	File_Buffer FILE;
	File_Buffer FILE2;
	File_Buffer FILE3;
	Init_Exit Start_End;
	Init_Exit Start_End2;
	Init_Exit Start_End3;
	String[] input = {"C:\\Users\\duswn\\eclipse-workspace\\csuedSource\\test.txt"};
	String[] input2 = {"C:\\Users\\duswn\\eclipse-workspace\\csuedSource\\test2.txt"};
	String[] input3 = {"C:\\Users\\duswn\\eclipse-workspace\\csuedSource\\test2.txt"};
	private final PrintStream systemOut = System.out;
	private ByteArrayOutputStream testOut;

	@Before
	public void setUp() throws Exception{
		testOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testOut));
		cmd = new Cmd_Driver();
		FILE = new File_Buffer();
		FILE2 = new File_Buffer();
		FILE3 =  new File_Buffer();
		Start_End    = new Init_Exit(input,FILE);
		Start_End2 = new Init_Exit(input2,FILE2);
		Start_End3 = new Init_Exit(input3,FILE3);
	}
	@After
	public void restoreStreams() throws IOException {
		System.setOut(systemOut);
	}

	@Test
	public void testCmd_Q(){
		//cmd_Q는 파일의 내용이 바뀌므로 다른 메소드를 테스트하는데 있어서 파일의 내용이 바뀌면 영향을 끼칠수 있어서 
		//cmd_Q에서만 FILE2객체를 만들어서 실행하였습니다.
		int nLines=1;
		int totalLines=FILE2.NumLins();
		FILE2.setUpdateFlag(true);
		
		
		try {
			cmd.Cmd_D(FILE2, nLines);
			Start_End2.Do_Update(FILE2);
			assertEquals("testing",FILE2.GetLine(1));
			assertEquals(totalLines-1,FILE2.NumLins());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}



	@Test
	public void testCmd_N() {
		int CLN = FILE.GetCLN();
		int nLines=2;
		System.out.println("asdad");
		cmd.Cmd_N(FILE, nLines);
		CLN += nLines;
		if( CLN > FILE.NumLins())
			CLN=FILE.NumLins();
		assertEquals(CLN, FILE.GetCLN());
	}

	@Test
	public void testCmd_B() {
		int CLN = FILE.GetCLN();
		int nLines = 3;
		cmd.Cmd_B(FILE, nLines);
		CLN -= nLines;
		if( CLN < 1)
			CLN=1;
		assertEquals(CLN,FILE.GetCLN());
	}

	@Test
	public void testCmd_W() {
		cmd.Cmd_W(FILE); //Cmd_W명령어를 사용해서 콘솔에 출력한다.
		assertEquals("At Edit File Line "+FILE.GetCLN()+System.getProperty("line.separator"),testOut.toString() );//콘솔에 출력된 문장과 비교한다.
		//Cmd_W명령어 사용시 println이라 개행문자를 비교하기 위해서 System.getProperty("line.separator")를 사용했다.
	}

	@Test
	public void testCmd_C() {
		cmd.Cmd_C(FILE);
		assertEquals("Total Edit File Lines: "+FILE.NumLins()+System.getProperty("line.separator"),testOut.toString());
	}

	@Test
	public void testCmd_L() {
		int CLN=FILE.GetCLN();
		int nLines=3;
		String str = "";
		
		for(int i=CLN;i<=nLines;i++) {
			str += FILE.GetLine(i)+System.getProperty("line.separator");
		}
		cmd.Cmd_L(FILE,nLines);
		assertEquals(str,testOut.toString());
	}

	@Test
	public void testCmd_S() {
		int CLN=FILE.GetCLN();
		int nLines=3;
		String str = "";
		
		for(int i=CLN;i<=nLines;i++) {
			str += FILE.GetLine(i)+System.getProperty("line.separator");
		}
		cmd.Cmd_S(FILE,nLines);
		assertEquals(str,testOut.toString());
	}

	@Test
	public void testCmd_D() {
		int totalLines=FILE.NumLins();
		int nLines=2;
		cmd.Cmd_D(FILE, nLines);
		assertEquals(totalLines-nLines,FILE.NumLins()); //에러 발견!! 둘이 같아야 하는데 코드상의 에러로 인해서 다르게 나옴.
	}

	@Test
	public void testCmd_A() {
		int CLN = FILE.GetCLN();
		int totalLines=FILE.NumLins();
		try {
			cmd.Cmd_A(FILE);
			assertEquals(totalLines+1,FILE.NumLins());//Add로인해 총 라인수가 1 증가한다.
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testCmd_F() {
		int CLN = FILE.GetCLN();
		int nLines = 3;
		String str = "are";
		cmd.Cmd_F(FILE, nLines, str);
		
		assertEquals(CLN+(nLines-1),FILE.GetCLN()); //코드상의 오류로 fail뜬다.
	}

	@Test
	public void testCmd_R() {
		int CLN = FILE.GetCLN();
		int nLines = 3;
		
		cmd.Cmd_R(FILE, nLines, "is", "are");
		
		assertEquals(CLN+(nLines-1),FILE.GetCLN());//CLN의 마지막 위치가 맞는지 테스트한다.
	}

	@Test
	public void testCmd_Y() {
		int CLN = FILE.GetCLN();
		int nLines =3;
		cmd.Cmd_Y(FILE, nLines);
		assertEquals(CLN+(nLines-1),FILE.GetCLN());//CLN의 마지막 위치가 맞는지 테스트한다.
	}

	@Test
	public void testCmd_Z() {
		int CLN = FILE.GetCLN();
		int nLines =3;
		cmd.Cmd_Z(FILE, nLines);
		assertEquals(CLN,FILE.GetCLN());
	}

	@Test
	public void testCmd_P() {
		int CLN = FILE.GetCLN();
		int nLines = 3;
		
		cmd.Cmd_Y(FILE, nLines);
		cmd.Cmd_P(FILE);
		assertEquals(CLN+ (nLines*2)-1,FILE.GetCLN());
		//Yarn에 저장한 문장을 P로 파일에 입력시 변화하는 CLN을 비교해서 테스팅한다.
	}

	@Test
	public void testCmd_I() {
		int CLN = FILE.GetCLN();
		int nLines = 3;
		
		cmd.Cmd_I(FILE);
		assertEquals(CLN,FILE.GetCLN());
		//cmd_i에 의해서는 CLN이 영향받지않는다.
	}

	@Test
	public void testCmd_K() {
		int CLN = FILE.GetCLN();
		
		cmd.Cmd_I(FILE);
		cmd.Cmd_K("My name are YeonjunKim");
		
		assertEquals(CLN,FILE.GetCLN());//cmd_k에 의해서 CLN은 영향받지 않는다.
		
	}

	@Test
	public void testCmd_O() {
		
		int nLines = 3;
		cmd.Cmd_O(FILE, nLines);
		assertEquals("is",FILE.GetLine(2)); //정렬 전 "is"는 3번째 라인이지만 정렬 후 "is"는 2번째 라인으로 변경된다.
	}

	@Test
	public void testCmd_M() {
		//cmd_M에 대해서는 M_left = 2, M_right =1인경우
		// 			  M_left = 0 , M_right = 0인 경우
		//  	      M_left = 1  M_right =2  
		//			위 3가지 경우 각각에 대해서 test를 진행하였습니다.
		int CLN = FILE.GetCLN();
		int M_left;
		int M_right;
		M_left=2;
		M_right=1;
		cmd.Cmd_M(M_left, M_right);
		
		assertEquals("REVERSED OR BACKWARDS COLUMN RANGES ARE ILLEGAL:  No action taken."+System.getProperty("line.separator")
		+"COMMAND NOT IMPLEMENTED (for F, R, O) YET."+System.getProperty("line.separator"),testOut.toString());
		
		
	}
	@Test
	public void testCmd_M2() { //M_left와 M_right 둘다 0인경우
		int CLN = FILE.GetCLN();
		int M_left;
		int M_right;
		M_left=0;
		M_right=0;
		cmd.Cmd_M(M_left, M_right);
		
		assertEquals("COLUMN VALUES MUST BE POSITIVE & NONZERO:  No action taken."+System.getProperty("line.separator")
		+"COMMAND NOT IMPLEMENTED (for F, R, O) YET."+System.getProperty("line.separator"),testOut.toString());
		
		
	}
	@Test
	public void testCmd_M3() {
		int CLN = FILE.GetCLN();
		int M_left;
		int M_right;
		M_left=1;
		M_right=2;
		cmd.Cmd_M(M_left, M_right);
		
		assertEquals("COMMAND NOT IMPLEMENTED (for F, R, O) YET."+System.getProperty("line.separator"),testOut.toString());
		
		
	}

}
