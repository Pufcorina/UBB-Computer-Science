% Lab #10, Prob & Stat. 
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
% a. Assuming sigma_1 = sigma_2, find a 100(1-alpha)% confidence interval 
% for the difference of the true means.
% b. Assuming sigma_1 not= sigma_2, find a 100(1-alpha)%$ confidence 
% interval for the difference of the true means.
% c. Find a 100(1-alpha)%$ confidence interval for the ratio of the 
% variances.
x1= [20,21.6*ones(1,2),21.7,22.4*ones(1,2),23.3,...
    23.4,24.5,24.8];
x2=[12.1,12.2,12.6,14,14.8*ones(1,2),15.4,17.7,...
    19.6*ones(1,2)];
n1=length(x1); n2=length(x2);
oneminusalpha=input('conf. level =');
alpha=1-oneminusalpha;
xm1=mean(x1); xm2=mean(x2); m=xm1-xm2;
v1=var(x1); v2=var(x2); r=v1/v2;

% part a.
sp=sqrt(((n1-1)*v1+(n2-1)*v2)/(n1+n2-2));
n=n1+n2-2;
t=tinv(1-alpha/2,n);
m1=m-t*sp*sqrt(1/n1+1/n2);
m2=m+t*sp*sqrt(1/n1+1/n2);
fprintf('Difference of sample means:%6.3f\n',m)
fprintf('Conf. interval for diff. of means, variances equal (m1,m2)=(%6.3f,%6.3f)\n',m1,m2)

% part b.
c=(v1/n1)/(v1/n1+v2/n2);
%nn=ceil(1/(c^2/(n1-1)+(1-c)^2/(n2-1)));
nn=1/(c^2/(n1-1)+(1-c)^2/(n2-1));
s=sqrt(v1/n1+v2/n2);
tt=tinv(1-alpha/2,nn);
mm1=m-tt*s; mm2=m+tt*s;
fprintf('Conf. interval for diff. of means, variances not equal (mm1,mm2)=(%6.3f,%6.3f)\n\n',mm1,mm2)

% part c.
f1=finv(1-alpha/2,n1-1,n2-1);
f2=finv(alpha/2,n1-1,n2-1);
vv1=r/f1; vv2=r/f2;
s1=sqrt(vv1); s2=sqrt(vv2);
fprintf('Ratio of sample variances:%6.3f\n',r)
fprintf('Conf. interval for ratio of variances (vv1,vv2)=(%6.3f,%6.3f)\n',vv1,vv2)
fprintf('Conf. interval for ratio of st. deviations (s1,s2)=(%6.3f,%6.3f)\n\n',s1,s2)