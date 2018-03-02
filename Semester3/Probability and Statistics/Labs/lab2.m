%problem 1
h=1/100;
x=0:h:3;
f=@(x)cos(x);
g=@(x)(x.*sin(x));
h=@(x)(x.^2/10);
%plot(x,f(x),'r',x,g(x),'g',x,h(x),'y');

%problem 2
a=input('a: ');
b=input('b: ');
n=input('n: ');

step=(b-a)/n
x=a:step:b
st=a:step:b-step
dr=a+step:step:b

mid=(st+dr)/2
A=[1:n; st; dr; mid]
fprintf('  Nr.  |  Subint.  |  Midp.  |\n');
fprintf('------------------------------\n');
fprintf('  %d      [%d  %d]     %2.2f\n', A);
