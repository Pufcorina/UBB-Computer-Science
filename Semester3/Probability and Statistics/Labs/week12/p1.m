%disp("Hello World");
X = [ 7, 7, 4, 5, 9, 9, 4, 12, 8, 1, 8, 7, 3, 13, 2, 1, 17, 7, 12, 5, 6, 2, 1, 13, 14, 10, 2, 4, 9, 11, 3, 5, 12, 6, 10, 7];
u0 = 9;
sigma = 5;

[H, p, ci, z]=ztest(X, u0,sigma, 'alpha',0.05,'tail', 'left');

if(H)
    disp('Mean smaller than 9');
else
    disp('Mean equal to 9');
end

[H2, p2, ci2, stats] = vartest(X, 5, 'alpha', 0.1, 'tail', 'both');
if(H2)
    disp('!=5')
else
    disp('==5')
end

%-----------------------------------

X2 = [99.8*ones(1,2), 99.9*ones(1,5),98.0*ones(1,3),100.1*ones(1,4),100.5*ones(1,2),100.0*ones(1,2),100.2*ones(1,2)];
miu0 = 99.4;
[H3, p3, ci3, stats2] = ttest(X2, miu0,'alpha', 0.01, 'tail', 'right');
if(H3)
    disp('Not cool')
else
    disp('Cool')
end