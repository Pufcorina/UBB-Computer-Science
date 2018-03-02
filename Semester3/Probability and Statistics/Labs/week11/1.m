clear;
conflevel = input('confidence level [0; 1] = '); % 1 - alpha
alpha = 1 - conflevel;
X1 = [22.4 21.7 24.5 23.4 21.6 23.3 22.4 21.6 24.8 20.0];
X2 = [17.7 14.8 19.6 19.6 12.1 14.8 15.4 12.6 14.0 12.2];
m1 = mean(X1);
m2 = mean(X2);
n1 = length(X1);
n2 = length(X2);
v1 = var(X1);
v2 = var(X2);