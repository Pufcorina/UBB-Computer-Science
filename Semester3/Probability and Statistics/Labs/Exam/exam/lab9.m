%vezi documentul "conf_int"
%laboratorul e rezolvat complet si corect :)

%ex. 1
alfa = 0.05;    %ales de noi, cred
x = [7 7 4 5 9 9 4 12 8 1 8 7 3 13 2 1 17 7 12 5 6 2 1 13 14 10 2 4 9 11 3 5 12 6 10 7];
sigma = 5;
x_barat = mean(x);
n = 36;
z_cu_indice = norminv(1-alfa/2);
capat_stanga = x_barat - (sigma/sqrt(n))*z_cu_indice;
capat_dreapta = x_barat + (sigma/sqrt(n))*z_cu_indice;
fprintf('[%f  %f]\n', capat_stanga, capat_dreapta);

%ex. 2
%folosesc a doua formula din document, cea cu s si t
alfa = 0.05;
x = [99.8*ones(1,2), 99.9*ones(1,5), 98.0*ones(1,3), 100.1*ones(1,4), 100.5*ones(1,2), 100.0*ones(1,2), 100.2*ones(1,2)];
s = std(x);
x_barat = mean(x);
n = 20;
t_cu_indice = tinv(1-alfa/2,n-1);
capat_stanga = x_barat-(s/sqrt(n))*t_cu_indice;
capat_dreapta = x_barat+(s/sqrt(n))*t_cu_indice;
fprintf('[%f  %f]\n', capat_stanga, capat_dreapta);

%ex. 3
x = [1.48 1.26 1.52 1.56 1.48 1.46 1.30 1.28 1.43 1.43 1.55 1.57 1.51 1.53 1.68 1.37 1.47 1.61 1.49 1.43 1.64 1.51 1.60 1.65 1.60 1.64 1.51 1.51 1.53 1.74];
%conf. interval for the variance
s_squared = var(x);
n = 30;
chi_1_squared = chi2inv(1-alfa/2,n-1);
chi_2_squared = chi2inv(alfa/2,n-1);
capat_stanga = ((n-1)*s_squared)/chi_1_squared;
capat_dreapta = ((n-1)*s_squared)/chi_2_squared;
fprintf('[%f  %f]\n', capat_stanga, capat_dreapta);
%conf. interval for the standard deviation
capat_stanga2 = sqrt(capat_stanga);
capat_dreapta2 = sqrt(capat_dreapta);
fprintf('[%f  %f]\n', capat_stanga2, capat_dreapta2);
