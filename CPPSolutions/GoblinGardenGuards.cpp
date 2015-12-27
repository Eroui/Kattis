#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

int main() {
	int numGoblins;
	cin >> numGoblins;
	int goblins[numGoblins][2];
	for (int i = 0; i < numGoblins; i++) {
		cin >> goblins[i][0];
		cin >> goblins[i][1];
	}
	int numSprinklers;
	cin >> numSprinklers;
	int sprinklers[numSprinklers][3];
	for (int i = 0; i < numSprinklers; i++) {
		cin >> sprinklers[i][0];
		cin >> sprinklers[i][1];
		cin >> sprinklers[i][2];
	}
	int count = 0;
	for (int g = 0; g < numGoblins; g++) {
		for (int s = 0; s < numSprinklers; s++) {
			int x = goblins[g][0] - sprinklers[s][0];
			int y = goblins[g][1] - sprinklers[s][1];
			if (x*x+y*y <= sprinklers[s][2]*sprinklers[s][2]) {
				count++;
				break;
			}
		}
	}
	cout << numGoblins-count;
	return 0;
}
