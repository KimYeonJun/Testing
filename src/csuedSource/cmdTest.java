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
	
	//Ŀ�� testing
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
		//cmd_Q�� ������ ������ �ٲ�Ƿ� �ٸ� �޼ҵ带 �׽�Ʈ�ϴµ� �־ ������ ������ �ٲ�� ������ ��ĥ�� �־ 
		//cmd_Q������ FILE2��ü�� ���� �����Ͽ����ϴ�.
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
		cmd.Cmd_W(FILE); //Cmd_W��ɾ ����ؼ� �ֿܼ� ����Ѵ�.
		assertEquals("At Edit File Line "+FILE.GetCLN()+System.getProperty("line.separator"),testOut.toString() );//�ֿܼ� ��µ� ����� ���Ѵ�.
		//Cmd_W��ɾ� ���� println�̶� ���๮�ڸ� ���ϱ� ���ؼ� System.getProperty("line.separator")�� ����ߴ�.
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
		assertEquals(totalLines-nLines,FILE.NumLins()); //���� �߰�!! ���� ���ƾ� �ϴµ� �ڵ���� ������ ���ؼ� �ٸ��� ����.
	}

	@Test
	public void testCmd_A() {
		int CLN = FILE.GetCLN();
		int totalLines=FILE.NumLins();
		try {
			cmd.Cmd_A(FILE);
			assertEquals(totalLines+1,FILE.NumLins());//Add������ �� ���μ��� 1 �����Ѵ�.
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
		
		assertEquals(CLN+(nLines-1),FILE.GetCLN()); //�ڵ���� ������ fail���.
	}

	@Test
	public void testCmd_R() {
		int CLN = FILE.GetCLN();
		int nLines = 3;
		
		cmd.Cmd_R(FILE, nLines, "is", "are");
		
		assertEquals(CLN+(nLines-1),FILE.GetCLN());//CLN�� ������ ��ġ�� �´��� �׽�Ʈ�Ѵ�.
	}

	@Test
	public void testCmd_Y() {
		int CLN = FILE.GetCLN();
		int nLines =3;
		cmd.Cmd_Y(FILE, nLines);
		assertEquals(CLN+(nLines-1),FILE.GetCLN());//CLN�� ������ ��ġ�� �´��� �׽�Ʈ�Ѵ�.
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
		//Yarn�� ������ ������ P�� ���Ͽ� �Է½� ��ȭ�ϴ� CLN�� ���ؼ� �׽����Ѵ�.
	}

	@Test
	public void testCmd_I() {
		int CLN = FILE.GetCLN();
		int nLines = 3;
		
		cmd.Cmd_I(FILE);
		assertEquals(CLN,FILE.GetCLN());
		//cmd_i�� ���ؼ��� CLN�� ��������ʴ´�.
	}

	@Test
	public void testCmd_K() {
		int CLN = FILE.GetCLN();
		
		cmd.Cmd_I(FILE);
		cmd.Cmd_K("My name are YeonjunKim");
		
		assertEquals(CLN,FILE.GetCLN());//cmd_k�� ���ؼ� CLN�� ������� �ʴ´�.
		
	}

	@Test
	public void testCmd_O() {
		
		int nLines = 3;
		cmd.Cmd_O(FILE, nLines);
		assertEquals("is",FILE.GetLine(2)); //���� �� "is"�� 3��° ���������� ���� �� "is"�� 2��° �������� ����ȴ�.
	}

	@Test
	public void testCmd_M() {
		//cmd_M�� ���ؼ��� M_left = 2, M_right =1�ΰ��
		// 			  M_left = 0 , M_right = 0�� ���
		//  	      M_left = 1  M_right =2  
		//			�� 3���� ��� ������ ���ؼ� test�� �����Ͽ����ϴ�.
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
	public void testCmd_M2() { //M_left�� M_right �Ѵ� 0�ΰ��
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
