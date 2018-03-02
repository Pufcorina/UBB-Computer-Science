% Generate X e Bern(p)
clear ALL
p = input('probability of success = ');
U = rand;
X = (U < p);
clear X;

% Generate a sample
N = input('number of simulations = ');
for i = 1:N
    U = rand;
    X(i) = (U < p);
end
UX = unique(X)
fr = hist(X, length(UX));
relfr = fr / N