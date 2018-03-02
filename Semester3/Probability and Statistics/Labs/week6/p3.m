% Generate X e Geo(p)
clear ALL
p = input('probability of success = ');
X = 0;
while(rand >= p)
    X = X+1;
end;
clear X;

% Generate a sample
N = input('number of simulations = ');
for i = 1:N
    X(i) = 0;
    while(rand >= p)
        X(i) = X(i)+1;
    end;
end

fprintf('%d ', X);
hist(X);
figure(2);
hist(geornd(p, 1, N));