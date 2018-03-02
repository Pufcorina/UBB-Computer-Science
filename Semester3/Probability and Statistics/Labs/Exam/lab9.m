%Problem 1
%our data
x = [7,7,4,5,9,9,4,12,8,1,8,7,3,13,2,1,17,7,12,5,6,2,1,13,14,10,2,4,9,11,3,5,12,6,10,7];
n = length(x);
%significance level 
alpha = 0.05;
sigma = 5;
%mean
xbar = mean(x);
%standard error
stderr = sigma/sqrt(n);
%confidence intervals
mean1 = xbar - stderr * norminv(1-alpha/2);
mean2 = xbar - stderr * norminv(alpha/2);
fprintf('The interval is (%f,%d)\n',mean1,mean2);

%Problem 2
y = [99.8*ones(1,2), 99.9*ones(1,5), 98*ones(1,3), 100.1*ones(1,4), 100.5*ones(1,2), 100*ones(1,2), 100.2*ones(1,2)];
n = length(y);
alpha = 0.05;
sigma = std(y);
xbar = mean(y);
stderr = sigma/sqrt(n);
mean1 = xbar - stderr * tinv(1-alpha/2,n-1);
mean2 = xbar - stderr * tinv(alpha/2,n-1);
fprintf('The interval is (%f,%d)\n',mean1,mean2);

%Problem 3

cl = input('Confidence level = '); % 1 - alpha
alpha = 1 - cl;
X = [1.48 1.26 1.52 1.56 1.48 1.46 1.30 1.28 1.43 1.43 1.55 1.57 1.51 1.53 1.68 1.37 1.47 1.61 1.49 1.43 1.64 1.51 1.60 1.65 1.60 1.64 1.51 1.51 1.53 1.74];
n = length(X);
% get the variance
s2 = var(X); 

q1 = chi2inv(1-alpha/2, n-1);
q2 = chi2inv(alpha/2, n-1);

ci1 = (n-1)*s2/q1;
ci2 = (n-1)*s2/q2;
fprintf('Confidence interval for the variance: (%.3f, %.3f)\n', ci1, ci2);
fprintf('Confidence interval for the standard deviation: (%.3f, %.3f)\n', sqrt(ci1), sqrt(ci2));
