%1. Generate N random numbers uniformly distributed on (a,b). 
%Find 
%a) the grouped frequency and relative frequency distribution; 
%b) the histogram; 
%c) the frequency polygon; 
%d) the mode; 
%e) the quartiles.

N = input('sample size = ');
a = input('a = ');
b = input('b(>a) = ');
x = unifrnd(a, b, 1, N); % sample

n = 1+10/3*log10(N); % number of classes (Sturge's rule)
id = 1:n;
xmin = min(x);
xmax = max(x);
limits = xmin:((xmax-xmin)/n):xmax;
leftlim = limits(id);
rightlim = limits(id+1);
[fr, mark] = hist(x, n);
hist(x, n); % draws histogram
hold on;
plot(mark, fr, 'r.-', 'LineWidth', 3);
relfr = fr/N;

res = [id; leftlim; rightlim; mark; fr; relfr];
fprintf(' Nr. |     Class    | Mark | Freq. | Rel.fr\n');
fprintf('%4d | [%3.2f; %3.2f] | %3.2f |  %4d | %3.2f\n', res);
fprintf('Mean = %3.4f\n', mean(x));

fprintf('Mode:\n');
idmode = find(fr == max(fr));
resm = [idmode; leftlim(idmode); rightlim(idmode); mark(idmode); fr(idmode); relfr(idmode)];
fprintf('%4d | [%3.2f; %3.2f] | %3.2f |  %4d | %3.2f\n', resm);
