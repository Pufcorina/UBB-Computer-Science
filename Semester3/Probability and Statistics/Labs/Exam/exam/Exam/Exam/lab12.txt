x1 = [22.4, 21.7, 24.5, 23.4, 21.6, 23.3, 22.4, 21.6, 24.8, 20.0];
x2 = [17.7, 14.8, 19.6, 19.6, 12.1, 14.8, 15.4, 12.6, 14.0, 12.2];
n1 = length(x1);
n2 = length(x2);
alpha = 0.05;

f1 = finv(alpha/2,n1-1,n2-1)
f2 = finv(1-alpha/2,n1-1,n2-1)
%Vartest2 e pentru matlab, in octave nu mereee
[h,p,ci,stats]=vartest2(x1, x2, alpha, 0)
%punem ultimul parametru 0 pentru 'both'

[h1,p1,ci1,stats1]=ttest2(x1,x2,alpha,1)
%punem ultimul parametru 1 sau 'right' pentru miu1>miu2, adica
%mean(x1)>mean(x2): does gas mileage seem to be higher, on average, 
%when premium gasoline is used -> premium(=x1) > normal(=x2)
%daca aveam premium<normal, puneam -1 sau 'left'

%because the resulting h is 0
%H0 is accepted, it seems the variances are equal
%H1 would have been accepted if h would've been 1, therefore the variances
%wouldn't have been equal

quantila=tinv(1-alpha,n1+n2-2)
fprintf("(%2.2f,inf)\n",quantila)