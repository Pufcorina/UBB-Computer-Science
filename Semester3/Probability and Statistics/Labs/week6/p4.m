% Generate X e NB(p)
clear ALL
n = input('rank of success = ');
p = input('probability of success = ');
for j = 1:n
    Y(j) = 0;
    while(rand >= p)
        Y(j) = Y(j)+1;
    end;
end
X = sum(Y);
clear X;

% Generate a sample
N = input('number of simulations = ');
for i = 1:N
    for j = 1:n
        Y(j) = 0;
        while(rand >= p)
            Y(j) = Y(j)+1;
        end;
    end
    X(i) = sum(Y);
end


fprintf('%d ', X);
hist(X);
figure(2);
hist(nbinrnd(n, p, 1, N));