clc;
x1 = [4.6 0.7 4.2 1.9 4.8 6.1 4.7 5.5 5.4];
x2 = [2.5 1.3 2.0 1.8 2.7 3.2 3.0 3.5 3.4];
n1=length(x1); 
n2=length(x2);

alpha = 0.05;

m1=mean(x1); 
m2=mean(x2);

v1=var(x1); 
v2=var(x2); 

f1=finv(alpha/2,n1-1,n2-1);
f2=finv(1-alpha/2,n1-1,n2-1);

% a)
% The null hypothesis H0: sigma1^2 = sigma2^2
% The alt. hypothesis H1: sigma1^2 ~= sigma2^2
% two-tailed for comparing the variances
[h,p,ci,stats] = vartest2(x1,x2,alpha,0);

fprintf('\n a) Comparing variances\n')
fprintf('Two-tailed test for comparing variances\n')

if h==0
    fprintf('h is %d\n', h)
    fprintf('So the null hypothesis is NOT REJECTED,\n')
    fprintf('i.e. the variances seem to be EQUAL\n')
    fprintf('the rejection region for F is (%6.4f,%6.4f)U(%6.4f,%6.4f)\n', -inf, f1, f2, inf)
    fprintf('the value of the test statistic F is %6.4f\n', stats.fstat)
    fprintf('the P-value for the variances test is %6.4f\n', p)
    
    % b) 
    % The null hypothesis H0: mu1 = mu2
    % The alt. hypothesis H1: mu1 > mu2
    % right-tailed for the difference of means
    
    n=n1+n2-2;
    
    t2=tinv(1-alpha,n); % quantile for right-tailed test (for rejection region)
    
    [hh,pp1,ci2,stats]=ttest2(x1,x2,alpha,1);
    
    fprintf('\nb). Comparing means when variances are equal\n')
    fprintf('Right-tailed test for the difference of means\n')
    fprintf(' hh is %d\n', hh)
    
    if hh==1
        fprintf('So the null hypothesis is REJECTED,\n') 
        fprintf('i.e. STEEL pipes LOOSE MORE heat than GLASS pipes\n')
    else
        fprintf('So the null hypothesis is not rejected,\n')
        fprintf('i.e. STEEL pipes DO NOT LOOSE MORE heat than GLASS pipes\n')
    end   
    
    fprintf('the rejection region for T is (%6.4f,%6.4f)\n', t2, inf)
    fprintf('the value of the test statistic T is %6.4f\n', stats.tstat)
    fprintf('the P-value of the test for diff. of means is %e\n', pp1)
else
    fprintf('h is %d\n', h)
    fprintf('So the null hypothesis is rejected,\n')
    fprintf('i.e. the VARIANCES seem to be NOT EQUAL\n')
    fprintf('the rejection region for F is (%6.4f,%6.4f)U(%6.4f,%6.4f)\n', -inf, f1, f2, inf)
    fprintf('the value of the test statistic F is %6.4f\n', stats.fstat)
    fprintf('the P-value for the variances test is %6.4f\n', p)
    
    % b). 
    % The null hypothesis H0: mu1 = mu2
    % The alt. hypothesis H1: mu1 > mu2
    % right-tailed for the difference of means 
    
    c=(v1/n1)/(v1/n1+v2/n2);
    n=c^2/(n1-1)+(1-c)^2/(n2-1);
    n=1/n;
    t2=tinv(1-alpha,n); % quantile for right-tailed test (for rejection region)
    
    [hh,pp2,ci2,stats]=ttest2(x1,x2,alpha,1,'unequal');
    
    fprintf('\nb). Comparing means when VARIANCES are NOT EQUAL\n')
    fprintf('Right-tailed test for the difference of means\n')
    fprintf('hh is %d\n', hh)
    
    if hh==1
        fprintf('So the null hypothesis is rejected,\n') 
        fprintf('i.e. STEEL pipes LOOSE MORE heat than GLASS pipes\n')
    else
        fprintf('So the null hypothesis is not rejected,\n')
        fprintf('i.e. STEEL pipes DO NOT LOOSE MORE heat than GLASS pipes\n')
    end  
    
    fprintf('the rejection region for T is (%6.4f,%6.4f)\n', t2, inf)
    fprintf('the value of the test statistic T is %6.4f\n', stats.tstat)
    fprintf('the P-value of the test for diff. of means is %e\n', pp2)

end

