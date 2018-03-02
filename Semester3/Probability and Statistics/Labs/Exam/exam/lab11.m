% DE TERMINAT
%find the rejection region, the value of the test statistic and the P-value of the test

%EX 1
x = [7 7 4 5 9 9 4 12 8 1 8 7 3 13 2 1 17 7 12 5 6 2 1 13 14 10 2 4 9 11 3 5 12 6 10 7];
x_barat = mean(x);
miu0 = 9;
s = std(x);
n = 36;
sigma = 5;

%alegem 'left' la punctul a pentru ca spune 'If a computer system cannot store at least 9 files, on average, it does not 
% meet the efficiency standard' => daca valoarea e mai MICA decat 9, nu e ok => verificam cu left

%alfa = 0.05:
fprintf('****************************\nEX 1, PUNCTUL A - NEW TEST\n');
tt = (x_barat-miu0)/(sigma/sqrt(n));
fprintf('\ntt = %f\n\n',tt);

[h,p,ci,zval] = ztest(x,9,sigma,'Alpha',0.05,'Tail','left');
fprintf('Tail = left.\nh = %d\np = %f\n',h,p);
fprintf('ci=%f\n',ci);
fprintf('zval = %f\n',zval);

%alfa = 0.01:
fprintf('\n\nALFA=0.01:\n');

[h,p,ci,zval] = ztest(x,9,sigma,'Alpha',0.01,'Tail','left');
fprintf('Tail = left.\nh = %d\np = %f\n',h,p);
fprintf('ci=%f\n',ci);
fprintf('zval = %f\n',zval);

%alfa = 0.05:
fprintf('****************************\nEX 1, PUNCTUL B - NEW TEST\n');
tt = (x_barat-miu0)/(s/sqrt(n));
fprintf('\ntt = %f\n\n',tt);

% la punctul b folosim 'both', la ex 2 folosim 'right'
