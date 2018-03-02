% a

clear all

x_standard = [46, 37, 39, 48, 47, 44, 35, 31, 44, 37];
x_new = [35, 33, 31, 35, 34, 30, 27, 32, 31, 31];

n_standard = length(x_standard);
n_new = length(x_new);

m_standard = mean(x_standard);
m_new = mean(x_new);

alpha = 0.05;

f1=finv(alpha/2,n_standard-1,n_new-1);
f2=finv(1-alpha/2,n_standard-1,n_new-1); % quantiles for two-tailed test (for rejection region)

% The null hypothesis H0: sigma1^2 = sigma2^2
% The alt. hypothesis H1: sigma1^2 ~= sigma2^2
% two-tailed for comparing the variances
[h,p,ci,stats]=vartest2(x_standard,x_new,alpha,0); %Two-sample F-test for equal variances

fprintf('\n part a. Comparing variances\n')
fprintf('Two-tailed test for comparing variances\n')
if h==0
    fprintf('h is %d\n', h)
    fprintf('So the null hypothesis is not rejected,\n')
    fprintf('i.e. the variances seem to be equal\n')
    fprintf('the rejection region for F is (%6.4f,%6.4f)U(%6.4f,%6.4f)\n', -inf, f1, f2, inf)
    fprintf('the value of the test statistic F is %6.4f\n', stats.fstat)
    fprintf('the P-value for the variances test is %6.4f\n', p)
else
    fprintf('h is %d\n', h)
    fprintf('So the null hypothesis is rejected,\n')
    fprintf('i.e. the variances seem to be different\n')
    fprintf('the rejection region for F is (%6.4f,%6.4f)U(%6.4f,%6.4f)\n', -inf, f1, f2, inf)
    fprintf('the value of the test statistic F is %6.4f\n', stats.fstat)
    fprintf('the P-value for the variances test is %6.4f\n', p)
    
    % b
    v_standard = var(x_standard);
    v_new = var(x_new); %variance
    
    % The null hypothesis H0: mu1 = mu2
    % The alt. hypothesis H1: mu1 > mu2
    % right-tailed for the difference of means 
    
    c = (v_standard/n_standard)/(v_standard/n_standard+v_new/n_new);
    n = c^2/(n_standard-1)+(1-c)^2/(n_new-1);
    n=1/n;
    t2=tinv(1-alpha,n); % quantile for right-tailed test (for rejection region)
    [hh,pp2,ci2,stats]=ttest2(x_standard,x_new,alpha,1,'unequal');
    
    fprintf('\n part b. Comparing means when variances are not equal\n')
    fprintf('Right-tailed test for the difference of means\n')
    fprintf(' hh is %d\n', hh)
    if hh==1
        fprintf('So the null hypothesis is rejected,\n') 
        fprintf('i.e. standard method is more efficient\n')
    else
        fprintf('So the null hypothesis is not rejected,\n')
        fprintf('i.e. new method is more efficient\n')
    end    
    fprintf('the rejection region for T is (%6.4f,%6.4f)\n', t2, inf)
    fprintf('the value of the test statistic T is %6.4f\n', stats.tstat)
    fprintf('the P-value of the test for diff. of means is %e\n', pp2)
end

