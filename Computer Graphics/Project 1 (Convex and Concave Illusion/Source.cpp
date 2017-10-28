#include <gl/glut.h>
#include <math.h>
using namespace std;

void display(void)
{
	double t, p = 3.141593 / 180;
	float color = 0.00555;
	int r = 45; //radius
	int x, y; //x and y coordinate for circle
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(color, color, color);
	glBegin(GL_LINES);
	for (y = r; y >= -r; y--) //y from r to -r, meaning entire diameter of circle
	{
		x = sqrt(pow(r, 2) - pow(y, 2));

		//Add and/or subtract from x and y values to place disks in different locations on image.
		glVertex2f(x - 100, y + 150); //Lines for first disk
		glVertex2f(-x - 100, y + 150);

		glVertex2f(x - 140, y - 150); //Lines for second disk..
		glVertex2f(-x - 140, y - 150);

		glVertex2f(x + 160, y);
		glVertex2f(-x + 160, y);

		//Increment or decrement color value to linearly change color of lines from black to white or white to black.
		color = color + 0.0109; //black to white
		glColor3f(color, color, color);
	}
	for (y = r; y >= -r; y--)
	{
		x = sqrt(pow(r, 2) - pow(y, 2));

		glVertex2f(x - 50, y);
		glVertex2f(-x - 50, y);

		glVertex2f(x + 120, y + 170);
		glVertex2f(-x + 120, y + 170);

		glVertex2f(x + 80, y - 160);
		glVertex2f(-x + 80, y - 160);

		color = color - 0.0109; //white to black
		glColor3f(color, color, color);
	}
	glEnd();
	glFlush();
}

void main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutInitWindowPosition(200, 100);
	glutCreateWindow("Project 1");
	glClearColor(0.5, 0.5, 0.5, 0.0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(-250.0, 250.0, -250.0, 250.0);
	glutDisplayFunc(display);
	glutMainLoop();
}