%vezi documentul "conf_int"
%laboratorul e rezolvat complet si corect :)

% a)
alfa = 0.05;    %ales de noi, cred
x = [ 1001.7, 975.0, 978.3, 988.3, 978.7, 988.9, 1000.3, 979.2, 968.9, 983.5, 999.2, 985.6]

%folosesc a doua formula din document, cea cu s si t
s = std(x);
x_barat = mean(x);
n = length(x);
t_cu_indice = tinv(1-alfa/2,n-1);
capat_stanga = x_barat-(s/sqrt(n))*t_cu_indice;
capat_dreapta = x_barat+(s/sqrt(n))*t_cu_indice;
fprintf('[%f  %f]\n', capat_stanga, capat_dreapta);

% b)
alpha = 0.01;

% 1. a)
% H0 : miu = 9
% H1 : miu < 9 (left-tailed test)
miu0 = 9; % test value

[h, p, ci, zval] = ttest(x, 995, alpha, 'left');

if h == 0
    fprintf('H0 is NOT rejected, i.e. they are faster\n');
else
    fprintf('H0 is rejected, i.e. they are not faster\n');
end
