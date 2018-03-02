% Lab #12, Prob & Stat.
% It is thought that the gas mileage obtained by a particular model of 
% automobile will be higher if unleaded premium gasoline is used in the 
% vehicle rather than regular unleaded gasoline. To gather evidence in 
% this matter, 10 cars are randomly selected from the assembly line and 
% tested using a specified brand of premium gasoline; 10 others are 
% randomly selected and tested using the brand's regular gasoline. Tests 
% are conducted under identical controlled conditions and gas mileages 
% for both types of gas are assumed independent and (approximately) 
% normally distributed. These data result:
%   Premium            Regular
% 22.4  21.7    !    17.7  14.8 
% 24.5  23.4    !    19.6  19.6 
% 21.6  23.3    !    12.1  14.8 
% 22.4  21.6    !    15.4  12.6 
% 24.8  20.0    !    14.0  12.2  
% Let 0 < alpha < 1.
% a. At the alpha significance level, is there evidence that the variances
% of the two populations are equal?
% b. Based on the result in part  a., at the same significance level, 
% does gas mileage seem to be higher, on average, when premium gasoline 
% is used?

clear all


x1= [20,21.6*ones(1,2),21.7,22.4*ones(1,2),23.3,...
    23.4,24.5,24.8];
x2=[12.1,12.2,12.6,14,14.8*ones(1,2),15.4,17.7,...
    19.6*ones(1,2)];

n1=length(x1); n2=length(x2);
alpha=input('significance level alpha =');
m1=mean(x1); m2=mean(x2);
v1=var(x1); v2=var(x2); 


f1=finv(alpha/2,n1-1,n2-1);
f2=finv(1-alpha/2,n1-1,n2-1); % quantiles for two-tailed test (for rejection region)

% part a. 
% The null hypothesis H0: sigma1^2 = sigma2^2
% The alt. hypothesis H1: sigma1^2 ~= sigma2^2
% two-tailed for comparing the variances
[h,p,ci,stats]=vartest2(x1,x2,alpha,0);


fprintf('\n part a. Comparing variances\n')
fprintf('Two-tailed test for comparing variances\n')
if h==0
    fprintf('h is %d\n', h)
    fprintf('So the null hypothesis is not rejected,\n')
    fprintf('i.e. the variances seem to be equal\n')
    fprintf('the rejection region for F is (%6.4f,%6.4f)U(%6.4f,%6.4f)\n', -inf, f1, f2, inf)
    fprintf('the value of the test statistic F is %6.4f\n', stats.fstat)
    fprintf('the P-value for the variances test is %6.4f\n', p)
  % part b. 
  % The null hypothesis H0: mu1 = mu2
  % The alt. hypothesis H1: mu1 > mu2
  % right-tailed for the difference of means
    
    n=n1+n2-2;
    
    t2=tinv(1-alpha,n); % quantile for right-tailed test (for rejection region)
    
    [hh,pp1,ci2,stats]=ttest2(x1,x2,alpha,1);
    fprintf('\n part b. Comparing means when variances are equal\n')
    fprintf('Right-tailed test for the difference of means\n')
    fprintf(' hh is %d\n', hh)
    if hh==1
        fprintf('So the null hypothesis is rejected,\n') 
        fprintf('i.e. gas mileage IS higher with premium gasoline\n')
    else
        fprintf('So the null hypothesis is not rejected,\n')
        fprintf('i.e. gas mileage IS NOT higher with premium gasoline\n')
    end    
    fprintf('the rejection region for T is (%6.4f,%6.4f)\n', t2, inf)
    fprintf('the value of the test statistic T is %6.4f\n', stats.tstat)
    fprintf('the P-value of the test for diff. of means is %e\n', pp1)
    
else
    fprintf('h is %d\n', h)
    fprintf('So the null hypothesis is rejected,\n')
    fprintf('i.e. the variances seem to be different\n')
    fprintf('the rejection region for F is (%6.4f,%6.4f)U(%6.4f,%6.4f)\n', -inf, f1, f2, inf)
    fprintf('the value of the test statistic F is %6.4f\n', stats.fstat)
    fprintf('the P-value for the variances test is %6.4f\n', p)

  % part b. 
  % The null hypothesis H0: mu1 = mu2
  % The alt. hypothesis H1: mu1 > mu2
  % right-tailed for the difference of means 
    c=(v1/n1)/(v1/n1+v2/n2);
    n=c^2/(n1-1)+(1-c)^2/(n2-1);
    n=1/n;
    t2=tinv(1-alpha,n); % quantile for right-tailed test (for rejection region)
    [hh,pp2,ci2,stats]=ttest2(x1,x2,alpha,1,'unequal');
    fprintf('\n part b. Comparing means when variances are not equal\n')
    fprintf('Right-tailed test for the difference of means\n')
    fprintf(' hh is %d\n', hh)
    if hh==1
        fprintf('So the null hypothesis is rejected,\n') 
        fprintf('i.e. gas mileage IS higher with premium gasoline\n')
    else
        fprintf('So the null hypothesis is not rejected,\n')
        fprintf('i.e. gas mileage IS NOT higher with premium gasoline\n')
    end    
    fprintf('the rejection region for T is (%6.4f,%6.4f)\n', t2, inf)
    fprintf('the value of the test statistic T is %6.4f\n', stats.tstat)
    fprintf('the P-value of the test for diff. of means is %e\n', pp2)

end









