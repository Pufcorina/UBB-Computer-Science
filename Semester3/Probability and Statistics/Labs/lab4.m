clear
x0 = input('x0 = ');
x1 = input('x1 = ');
x2 = input('x2 = ');
alpha = input('alpha = ');
beta = input('beta = ');
option_distribution = input('option(normal/student/n/fischer): ', 's');

switch option_distribution
    case 'normal'
        fprintf('normal case\n');
        mu = input('introdu mu: ');
        sigma = input('introdu sigma: ');
        pa = normcdf(x0, mu, sigma);
        fprintf('Probabilitatea a) %f\n', pa);
        pb = 1 - pa;
        fprintf('Probabilitatea b) %f\n', pb);
        pc = normcdf(x2, mu, sigma) - normcdf(x1, mu, sigma);
        fprintf('Probabilitatea c) %f\n', pc);
        pd = 1 - pc;
        fprintf('Probabilitatea d) %f\n', pd);
        pe = norminv(alpha, mu, sigma);
        fprintf('Probabilitatea e) %f\n', pe);
        pf = norminv(1 - beta, mu, sigma);
        fprintf('Probabilitatea f) %f\n', pf);
        
    case 'student'
        fprintf('student case\n');
    case 'fischer'
        fprintf('fischer case\n');
    otherwise
        fprintf('ai introdus gresit\n');
end