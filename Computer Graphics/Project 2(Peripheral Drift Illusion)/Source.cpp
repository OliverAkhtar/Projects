#include <gl/glut.h>
using namespace std;

GLfloat rectangle[4][3] = {{5,0,0},{5,20,0},{-5,20,0},{-5,0,0}}; //vertices of rectanlge
GLfloat rightBorder[12][3] = { {0,20,0}, {0,22,0}, {7,22,0}, {7,20,0}, {5,20,0}, {7,20,0}, {7,0,0}, {5,0,0}, {0,0,0}, //vertices of border
							   {7,0,0}, {7,-2,0}, {0,-2,0} };
GLfloat leftBorder[12][3] = { {0,20,0}, {0,22,0}, {-7,22,0}, {-7,20,0}, {-5,20,0}, {-7,20,0}, {-7,0,0}, {-5,0,0},
							  {0,0,0}, {-7,0,0}, {-7,-2,0}, {0,-2,0} };



void draw_borders(bool colorSwitch)
{
	if (colorSwitch == 1) //set color of border to black or white
		glColor3f(1.0, 1.0, 1.0);
	else if (colorSwitch == 0)
		glColor3f(0.0, 0.0, 0.0);

	glBegin(GL_QUADS);
	for (int i = 0; i < 12; i++)
		glVertex3fv(rightBorder[i]);
	glEnd();

	if (colorSwitch == 1) //switch color for other border
		glColor3f(0.0, 0.0, 0.0);
	else if (colorSwitch == 0)
		glColor3f(1.0, 1.0, 1.0);

	glBegin(GL_QUADS);
	for (int i = 0; i < 12; i++)
		glVertex3fv(leftBorder[i]);
	glEnd();
}

void draw_rectangle(bool colorSwitch)
{
	glColor3f(0.0, 0.0, 1.0);
	glBegin(GL_POLYGON);
	for (int i = 0; i < 5; i++)
		glVertex3fv(rectangle[i]);
	glEnd();

	draw_borders(colorSwitch);
}

void draw_ring(bool colorSwitch)
{
	for (int r = 0; r < 360; r += 45)
	{
		glPushMatrix();
		glRotated(r, 0, 0, 1);
		glTranslated(0, 30, 0);
		draw_rectangle(colorSwitch);
		glPopMatrix();
	}
}

void display(void)
{
	bool colorSwitch = 1;
	glClear(GL_COLOR_BUFFER_BIT);
	for (int i = 0; i < 4; i++) //1st row of rings
	{
		glPushMatrix();
		glTranslated(-225 + i*150, 75, 0);
		draw_ring(colorSwitch);
		glPopMatrix();
		colorSwitch = !colorSwitch;
	}
	colorSwitch = 0;
	for (int i = 0; i < 4; i++) //2nd row
	{
		glPushMatrix();
		glTranslated(-225 + i*150, -75, 0);
		draw_ring(colorSwitch);
		glPopMatrix();
		colorSwitch = !colorSwitch;
	}
	glFlush();
}

void main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(600, 300);
	glutInitWindowPosition(200, 100);
	glutCreateWindow("Project 2");
	glClearColor(1.0, 1.0, 0.0, 0.0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(-300.0, 300.0, -150.0, 150.0);
	glutDisplayFunc(display);
	glutMainLoop();
}