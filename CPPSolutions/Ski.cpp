#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

int main() {
	int numSlopes;
	cin >> numSlopes;
	int numQueries;
	cin >> numQueries;
	int heights[numSlopes+1];
	long fun[numSlopes];
	int costs[numSlopes];
	long sums[numSlopes];
	long maxSums[numSlopes];
	for (int i = 0; i < numSlopes+1; i++) {
		cin >> heights[i];
		if (i > 0) {
			fun[i-1] = heights[i-1] - heights[i];
			fun[i-1] *= abs(fun[i-1]);
			costs[i-1] = heights[i-1] + heights[i];
		}
	}
	for (int i = 0; i < numSlopes; i++) {
		if (i > 0) {
			sums[i] = max(sums[i-1]+fun[i], fun[i]);
			maxSums[i] = max(sums[i], maxSums[i-1]);
		} else {
			sums[i] = max(long(0), fun[i]);
			maxSums[i] = sums[i];
		}
	}
	for (int q = 0; q < numQueries; q++) {
		int op;
		cin >> op;
		if (op == -1) {
			break;
		}
		if (op == 0) {
			int i;
			cin >> i;
			int newHeight;
			cin >> newHeight;
			heights[i] = newHeight;
			if (i < numSlopes) {
				fun[i] = heights[i] - heights[i+1];
				fun[i] *= abs(fun[i]);
				costs[i] = heights[i] + heights[i+1];
			}
			if (i > 0) {
				fun[i-1] = heights[i-1] - heights[i];
				fun[i-1] *= abs(fun[i-1]);
				costs[i-1] = heights[i-1] + heights[i];
				i--;
			}
			for (; i < numSlopes; i++) {
				if (i > 0) {
					sums[i] = max(sums[i-1]+fun[i], fun[i]);
					maxSums[i] = max(sums[i], maxSums[i-1]);
				} else {
					sums[i] = max(long(0), fun[i]);
					maxSums[i] = sums[i];
				}
			}
		} else {
			int cabin;
			cin >> cabin;
			long budget;
			cin >> budget;
			int i;
			for (i = cabin; i < numSlopes; i++) {
				if (budget-costs[i] < 0) {
					break;
				}
				budget -= costs[i];
			}
			if (i > 0) {
				cout << maxSums[i-1] << endl;
			} else {
				cout << 0 << endl;
			}
		}
	}
	return 0;
}
