%vezi documentul "conf_int"
%laboratorul e rezolvat complet si corect :)

%punctul a
alfa = 0.05;
x_premium = [22.4 21.7 24.5 23.4 21.6 23.3 22.4 21.6 24.8 20.0];
x_regular = [17.7 14.8 19.6 19.6 12.1 14.8 15.4 12.6 14.0 12.2];
x1_barat = mean(x_premium);
x2_barat = mean(x_regular);
n1 = 10;
n2 = 10;
t_cu_indice = tinv(1-alfa/2,n1+n2-2);
s1 = std(x_premium);
s2 = std(x_regular);
sp = sqrt(((n1-1)*s1*s1+(n2-1)*s2*s2)/(n1+n2-2));
capat_stanga = x1_barat-x2_barat-t_cu_indice*sp*sqrt(1/n1+1/n2);
capat_dreapta = x1_barat-x2_barat+t_cu_indice*sp*sqrt(1/n1+1/n2);
fprintf('[%f  %f]\n', capat_stanga, capat_dreapta);

%punctul b
c = ((s1*s1)/n1)/((s1*s1)/n1+(s2*s2)/n2);
invers_n = (c*c)/(n1-1)+((1-c)*(1-c))/(n2-1);
n =1/invers_n;
t_cu_indice = tinv(1-alfa/2,n);
capat_stanga = x1_barat-x2_barat-t_cu_indice*sqrt((s1*s1)/n1+(s2*s2)/n2);
capat_dreapta = x1_barat-x2_barat+t_cu_indice*sqrt((s1*s1)/n1+(s2*s2)/n2);
fprintf('[%f  %f]\n', capat_stanga, capat_dreapta);

%punctul c
f_cu_indice = finv(1-alfa/2,n1-1,n2-1);
capat_stanga = (1/f_cu_indice)*((s1*s1)/(s2*s2));
f_cu_indice = finv(alfa/2,n1-1,n2-1);
capat_dreapta = (1/f_cu_indice)*((s1*s1)/(s2*s2));
fprintf('[%f  %f]\n', capat_stanga, capat_dreapta);
