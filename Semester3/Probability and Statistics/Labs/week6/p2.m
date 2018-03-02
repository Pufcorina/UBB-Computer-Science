% Generate X e Bino(p)
clear ALL
n = input('number of trials = ');
p = input('probability of success = ');
U = rand(n, 1);
X = sum(U < p);
clear X;

% Generate a sample
N = input('number of simulations = ');
for i = 1:N
    U = rand(n, 1);
    X(i) = sum(U < p);
end
UX = unique(X);
fr = hist(X, length(UX));
relfr = fr / N;

% Compare graphically to the Bino(n,p) distribution
xpdf = 0:n;
ypdf = binopdf(xpdf, n, p);
clf;
plot(xpdf, ypdf, 'bo', UX, relfr, 'rx', 'MarkerSize', 10);
legend('binopdf', 'simulation');