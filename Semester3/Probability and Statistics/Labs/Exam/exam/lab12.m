%laborator complet si corect :)

%a) H0: sigma1^2 = sigma2^2  (the variances are equal)
%   H1: sigma1^2 =/= sigma2^2   => folosim two-tailed test
x1 = [22.4 21.7 24.5 23.4 21.6 23.3 22.4 21.6 24.8 20.0];
x2 = [17.7 14.8 19.6 19.6 12.1 14.8 15.4 12.6 14.0 12.2];
n1 = length(x1);
n2 = length(x2);
alpha = 0.05;   %the significance level
%folosim testul 4
f1 = finv(alpha/2, n1-1, n2-1);     %primul parametru -> pentru ca two-tailed test
f2 = finv(1-alpha/2, n1-1, n2-1);
[h, p, ci, stats] = vartest2(x1, x2, alpha, 'both');
%stats.fstat = TT
fprintf('a)\n');
fprintf('-- The decision is h=%d\n', h);   %h=0 => H0 accepted, h=1 => H1 accepted
fprintf('-- The p-value of the test is p=%f\n',p);
fprintf('-- The confidence interval is ci=[%f, %f]\n',ci);
fprintf('-- The value of the statistics is TT=%f\n',stats.fstat);
fprintf('-- The rejection region is (-inf,%f)U(%f,inf)\n\n',f1,f2);

%b)on average => lucram cu mu de data asta
%  H0: mu1 = mu2
%  H1: mu1 > mu2 (premium>regular)
%punctul a => h=0 => H0 de la a) accepted => equal variances
[h,p,ci,stats] = ttest2(x1,x2,alpha,'right');  %right pentru ca testam mu1>mu2, deci '>'
fprintf('b)\n');
fprintf('-- The decision is h=%d\n', h);   %h=0 => H0 accepted, h=1 => H1 accepted
fprintf('-- The p-value of the test is p=%f\n',p);
fprintf('-- The confidence interval is ci=[%f, %f]\n',ci);
fprintf('-- The value of the statistics is TT=%f\n',stats.tstat);
%rejection region -> ne uitam la formula de la right-tailed test, pentru
%RR (rejection region). Pentru tt(1-alfa), folosim formula de la punctul 3,
%a doua formula (datorita rezultatului de la a)
t = tinv(1-alpha,n1+n2-2);
fprintf('-- The rejection region is (%f,inf)\n\n',t);