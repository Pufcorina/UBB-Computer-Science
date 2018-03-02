clear;
alpha = input('significance level (0; 1) = ');

% 1. a)
% H0 : miu = 9
% H1 : miu < 9 (left-tailed test)
X = [7 7 4 5 9 9 ...
	 4 12 8 1 8 7 ...
     3 13 2 1 17 7 ...
     12 5 6 2 1 13 ...
     14 10 2 4 9 11 ...
     3 5 12 6 10 7];
sigma = 5; % sigma known
miu0 = 9; % test value

[h, p, ci, zval] = ztest(X, miu0, sigma, alpha, 'left');

if h == 0
    fprintf('H0 is NOT rejected, i.e. the standard is met\n');
else
    fprintf('H0 is rejected, i.e. the standard is NOT met\n');
end

fprintf('P-value is %3.4f\n', p);
fprintf('Observed value of the test statistics is %3.4f\n', zval);

qa = norminv(alpha, 0, 1);
fprintf('Rejection region is (-inf, %3.4f)\n', qa);

% ---------------
% 1. b)
% H0 :  sigma = 5 <=> sigma^2 = 25
% H1 : sigma != 5 <=> sigma^2 != 25 (two-tailed test)
var0 = sigma^2;
[h, p, ci, stats] = vartest(X, var0, alpha, 'both');

if h == 0
    fprintf('H0 is NOT rejected, i.e. the assumption on sigma seems to be correct\n');
else
    fprintf('H0 is rejected, i.e. the assumption on sigma does NOT seem to be correct\n');
end

fprintf('P-value is %3.4f\n', p);
fprintf('Observed value of the test statistics is %3.4f\n', stats.chisqstat);
q1 = chi2inv(alpha/2, stats.df);
q2 = chi2inv(1-alpha/2, stats.df);
fprintf('Rejection region is (-inf, %3.4f) U (%3.4f, +inf)\n', q1, q2);

% ---------------
% 2.


clear;
alpha = input('significance level (0; 1) = ');
% H0 : miu = 99.4
% H1 : miu > 99.4 (right-tailed test)
X = [99.8 * ones(1,2), ...
     99.9 * ones(1,5), ...
     98.0 * ones(1,3), ...
     100.1 * ones(1,4), ...
     100.5 * ones(1,2), ...
     100.0 * ones(1,2), ...
     100.2 * ones(1,2)];
miu0 = 99.4;
 
[h, p, ci, stats] = ttest(X, miu0, alpha, 'right');
tstat = stats.tstat;

if h == 0
    fprintf('H0 is NOT rejected, i.e. the center will accept the energy bars\n');
else
    fprintf('H0 is rejected, i.e. the center will NOT accept the energy bars\n');
end

fprintf('P-value is %3.4f\n', p);
fprintf('Observed value of the test statistics is %3.4f\n', tstat);

df = stats.df;
qa = tinv(1-alpha, df);
fprintf('Rejection region is (%3.4f; +inf)\n', qa);




