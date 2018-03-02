% Lab #9. Prob & Stat.
% Problem 1.
% In a study of the size of various computer systems, the random variable
% $X$, the number of files stored, is considered. Past experience 
% indicates that sigma = 5. These data are obtained:
%  7  7  4  5  9  9
%  4 12  8  1  8  7
%  3 13  2  1 17  7
% 12  5  6  2  1 13
% 14 10  2  4  9 11
%  3  5 12  6 10  7
% Find a 100(1-alpha)% confidence interval for the average number of 
% files stored.
x= [7, 7, 4, 5, 9, 9, 4, 12, 8, 1, 8, 7, 3, 13, 2, 1, 17, 7,...
   12, 5, 6, 2, 1, 13, 14, 10, 2, 4, 9, 11, 3, 5, 12, 6, 10, 7];
% sample size 
n=length(x);
% confidence level
% oneminusalpha=input('conf. level=');
% % significance level
% alpha=1-oneminusalpha;
alpha=1- input('conf. level=');
% population standard deviation
sigma=5;
% sample mean
samp_m=mean(x);
% standard error
stand_er=sigma/sqrt(n);
fprintf('\n Problem 1.\n')
fprintf(' the sample mean is: %6.3f\n',samp_m)
fprintf(' the standard error is: %6.3f\n',stand_er)
% limits of the confidence interval
m1=mean(x)+norminv(alpha/2)*stand_er; %or m1=mean(x)-norminv(1-alpha/2)*stand_er; 
m2=mean(x)-norminv(alpha/2)*stand_er;
% fprintf(' at the confidence level %3.2f\n',oneminusalpha)
fprintf(' the confidence interval for the mean is: (m1,m2)=(%6.3f,%6.3f)\n',m1,m2)


% Problem 2.
% The weights of chocolate bars of a certain brand are studied. A sample 
% of 20 yields the following data (in grams):
% X = 99.8  99.9  98.0  100.1  100.5  100.0  100.2
%      2     5      3     4      2      2      2
% Assuming the weights of the chocolate bars are approximately normally 
% distributed, find a 100(1-alpha) confidence interval for the average
% weight of the chocolate bars

x= [ 99.8*ones(1,2), 99.9*ones(1,5), 98.0*ones(1,3), 100.1*ones(1,4),...
    100.5*ones(1,2), 100.0*ones(1,2), 100.2*ones(1,2)];
% sample size 
n=length(x);
% confidence level
oneminusalpha=input('conf. level =');
% significance level
alpha=1-oneminusalpha;
% sample mean and standard deviation
samp_m=mean(x);
samp_std=std(x);
% standard error
stand_er=samp_std/sqrt(n);
fprintf('\n Problem 2.\n')
fprintf(' the sample mean is: %6.3f\n',samp_m)
fprintf(' the standard error is: %6.3f\n',stand_er)
% limits of the confidence interval
m1=mean(x)+tinv(alpha/2,n-1)*stand_er;
m2=mean(x)-tinv(alpha/2,n-1)*stand_er;
fprintf(' at the confidence level %3.2f\n',oneminusalpha)
fprintf(' the confidence interval for the mean is: (m1_a,m2_a)=(%6.3f,%6.3f)\n',m1,m2)

% Problem 3.
% When programming from a terminal, one random variable of concern is the 
% response time (in seconds). For one particular installation, a  
% (repeated) random sample yields the following data:
% 1.48  1.26  1.52	 1.56	1.48  1.46 
% 1.30  1.28  1.43	 1.43	1.55  1.57 
% 1.51  1.53  1.68	 1.37   1.47  1.61
% 1.49  1.43  1.64	 1.51   1.60  1.65 
% 1.60  1.64  1.51	 1.51   1.53  1.74 
% Assuming the response times of the terminals are (approximately) normally 
% distributed, find 100(1-alpha)% confidence intervals for the 
% variance and for the standard deviation (0< alpha < 1).
x= [1.26,1.28,1.3,1.37,1.43*ones(1,3),1.46,1.47,...
    1.48*ones(1,2),1.49,1.51*ones(1,4),1.52,...
    1.53*ones(1,2),1.55,1.56,1.57,1.6*ones(1,2),...
    1.61,1.64*ones(1,2),1.65,1.68,1.74];
n=length(x);
oneminusalpha=input('conf. level=');
alpha=1-oneminusalpha;
v=var(x);
c1=chi2inv(1-alpha/2,n-1);
c2=chi2inv(alpha/2,n-1);
v1=(n-1)*v/c1; v2=(n-1)*v/c2;
s1=sqrt(v1); s2=sqrt(v2);
fprintf('\n Problem 3.\n')
fprintf(' conf. interval for variances (v1,v2)=(%6.3f,%6.3f)\n',v1,v2)
fprintf(' conf. interval for st. deviations (s1,s2)=(%6.3f,%6.3f)\n\n',s1,s2)