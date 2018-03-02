n=7;
x=0:n;
p=0.7;
plot(x, binopdf(x,n,p), '*r');
hold on;
stairs(x,binocdf(x,n,p));
legend('prob','trial');