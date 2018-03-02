clear;
conflevel = input('confidence level [0; 1] = '); % 1 - alpha
alpha = 1 - conflevel;
X = [1.48 1.26 1.52 1.56 1.48 1.46 1.30 1.28 1.43 1.43 1.55 1.57 1.51 1.53 1.68 1.37 1.47 1.61 1.49 1.43 1.64 1.51 1.60 1.65 1.60 1.64 1.51 1.51 1.53 1.74];
n = length(X);
s2 = var(X); 
t1 = chi2inv(1 - alpha/2, n-1);
t2 = chi2inv(alpha/2, n-1);
ci1 = (n-1)*s2/t1;
ci2 = (n-1)*s2/t2;

fprintf('C.I. for the variance: (%3.4f, %3.4f)\n', ci1, ci2);
fprintf('C.I. for the variance: (%3.4f, %3.4f)\n', sqrt(ci1), sqrt(ci2));