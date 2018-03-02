% Lab #11, Prob & Stat. 
% Problem 1.
% In a study of the size of various computer systems, the random variable
% $X$, the number of files stored, is considered. If they cannot store at 
% least 9 files, on average, they don't meet the efficiency standard
% and they have to be replaced. Past experience 
% indicates that sigma = 5. These data are obtained:
%  7  7  4  5  9  9
%  4 12  8  1  8  7
%  3 13  2  1 17  7
% 12  5  6  2  1 13
% 14 10  2  4  9 11
%  3  5 12  6 10  7
% a. At the 5% significance level, does the data suggest that the standard 
% is met? What about at 1%? (Find the rejection region and the value of 
% the test statistic)
% b. What is the P-value of this test?

% The null hypothesis H0: mu = 9
% the alt. hypothesis H1: mu < 9. This is a left-tailed test for mu.

clear all

fprintf(' Problem 1: ')
fprintf('\n Left-tailed test for the mean\n')
x= [7, 7, 4, 5, 9, 9, 4, 12, 8, 1, 8, 7, 3, 13, 2, 1, 17, 7,...
   12, 5, 6, 2, 1, 13, 14, 10, 2, 4, 9, 11, 3, 5, 12, 6, 10, 7];
n=length(x);
sigma=5;
alpha=input(' significance level alpha =');

% a)
m0=input(' test value m0 =');
% m0 is in this case 9.
m=mean(x);
st=std(x);
[h,c,ci,zstat]=ztest(x,m0,sigma,alpha,-1);
z2=norminv(alpha); % quantile for left-tailed test.
fprintf(' h is %d', h)
if h==1
    fprintf('\n So the null hypothesis is rejected,\n') 
    fprintf(' i.e. the data suggests that the standard IS NOT met.\n')
else
    fprintf('\n So the null hypothesis is not rejected,\n')
    fprintf('i.e. the data suggests that the standard IS  met.\n')
end    
% fprintf(' the confidence interval for mu is (%4.4f,%4.4f)\n', ci)
fprintf(' the rejection region is (%4.4f,%4.4f)\n', -inf, z2)
fprintf(' the value of the test statistic z is %4.4f\n', zstat)
fprintf(' the P-value of the test is %4.4f\n', c)

% b)
fprintf('\n\n Test for variance\n')

% test for variance
v=var(x);
v0=input(' test value V0 = ');
% v0 is in this case 25.

% The null hypothesis H0: V = v0
% the alt. hypothesis H1: V ~= v0. This is a two-tailed test for V.
fprintf(' Two-tailed test for the variance\n')

c1=chi2inv(alpha/2,n-1);
c2=chi2inv(1-alpha/2,n-1); % quantiles for two-tailed test.
[h,p,ci,stats]=vartest(x,v0,alpha,0);

RR=[-Inf;c1;c2;Inf]; % rejection region for two-tailed test.
fprintf(' h is %d', h)
if h==1
    fprintf('\n So the null hypothesis is rejected,\n') 
    fprintf(' i.e. the variance IS NOT equal to %3.6f.\n',v0)
else
    fprintf('\n So the null hypothesis is not rejected,\n')
    fprintf(' i.e. the variance IS equal to %3.6f.\n',v0)
end 
fprintf(' the rejection region is (%4.4f,%4.4f)U(%4.4f,%4.4f)\n', RR)
fprintf(' the value of the test statistic chi2 is %4.4f\n', stats.chisqstat)
fprintf(' the P-value of the test is %4.4f\n', p)


% Problem 2.
% A fitness center purchases energy bars, but only if their
% average weight does not exceed 99.4 grams.
% A random sample 
% of 20 yields the following data (in grams):
% X = 99.8  99.9  98.0  100.1  100.5  100.0  100.2
%      2     5      3     4      2      2      2
% Assume the weights of the energy bars are approximately normally 
% distributed. 
% At the 5% significance level, does the data suggest that the center 
% will accept these energy bars?
% What about at 1%? 
% The null hypothesis H0: mu = 99.4
% the alt. hypothesis H1: mu > 99.4. This is a right-tailed test for mu.
x= [ 99.8*ones(1,2), 99.9*ones(1,5), 98.0*ones(1,3), 100.1*ones(1,4),...
    100.5*ones(1,2), 100.0*ones(1,2), 100.2*ones(1,2)];
n=length(x);
fprintf('\n Problem 2: \n')
alpha=input(' significance level alpha =');
m0=input(' test value m0 =');
% m0 is in this case 99.4.
[h,c,ci,stats]=ttest(x,m0,alpha,1);

t1=tinv(1-alpha,n-1); % quantile for right-tailed test.
RR=[t1, Inf]; % rejection region for right-tailed test
fprintf(' h is %d', h)
if h==1
    fprintf('\n So the null hypothesis is rejected,\n') 
    fprintf(' i.e. the data suggests that the bars WILL NOT be accepted.\n')
else
    fprintf('\n So the null hypothesis is not rejected,\n')
    fprintf(' i.e. the data suggests that the bars WILL be accepted.\n')
end    
%fprintf(' the confidence interval for mu is (%4.4f,%4.4f)\n', ci)
fprintf(' the rejection region is (%4.4f,%4.4f)\n', RR)
fprintf(' the value of the test statistic t is %4.4f\n', stats.tstat)
fprintf(' the P-value of the test is %4.4f\n\n\n\n', c)
