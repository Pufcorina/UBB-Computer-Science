x1 = [4.6, 0.7, 4.2, 1.9, 4.8, 6.1, 4.7, 5.5, 5.4];
x2 = [2.5, 1.3, 2.0, 1.8, 2.7, 3.2, 3.0, 3.5, 3.4];
n1 = length(x1);
n2 = length(x2);
alpha = 0.05;

f1 = finv(alpha/2,n1-1,n2-1);
f2 = finv(1-alpha/2,n1-1,n2-1);
% quantiles for two-tailed test (for rejection region)

[h,p,ci,stats]=vartest2(x1, x2, alpha, 0);
%punem ultimul parametru 0 pentru 'both'

if h == 0
    fprintf('H0 is NOT rejected, i.e. the variances seem to be equal\n');
    fprintf('P-value is %3.4f\n', p);
    fprintf('Observed value of the test statistics is %3.4f\n', stats.fstat);
    fprintf('Rejection region is (-inf, %3.4f) U (%3.4f, +inf)\n', f1, f2);

    n1 = length(x1);
    n2 = length(x2);
    alpha = 0.05;
    xm1 = mean(x1);
    xm2 = mean(x2);
    m = xm1-xm2;
    v1 = var(x1);
    v2 = var(x2);
    sp = sqrt(((n1-1)*v1+(n2-1)*v2)/(n1+n2-2));
    t = tinv(1-alpha/2,n1+n2-2);
    m1 = m-t*sp*sqrt(1/n1+1/n2);
    m2 = m+t*sp*sqrt(1/n1+1/n2);
    fprintf('Confidence interval for the difference of the means with equal standard deviations: %f %f\n',m1,m2);

else
    fprintf('H0 is rejected, i.e. the variances seem to NOT be equal\n');
    fprintf('P-value is %3.4f\n', p);
    fprintf('Observed value of the test statistics is %3.4f\n', stats.fstat);
    fprintf('Rejection region is (-inf, %3.4f) U (%3.4f, +inf)\n', f1, f2);

    n1 = length(x1);
    n2 = length(x2);
    alpha = 0.05;
    xm1 = mean(x1);
    xm2 = mean(x2);
    v1 = var(x1);
    v2 = var(x2);
    c = (v1/n1) / (v1/n1 + v2/n2);
    n = 1/(c^2/(n1-1) + (1-c)^2/(n2-1));
    t1 = tinv(1 - alpha/2, n);
    t2 = tinv(alpha/2, n); % t2 = -t1
    ci1 = xm1 - xm2 - t1 * sqrt(v1/n1 + v2/n2);
    ci2 = xm1 - xm2 - t2 * sqrt(v1/n1 + v2/n2);
    fprintf('Confidence interval for the difference of the means with different standard deviations: %f %f\n', ci1, ci2);
end

