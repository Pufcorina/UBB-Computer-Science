% Ridicare la putere in timp logaritmic

% pow_logN(n, p, modulo) =
% 	n, p = 1
% 	1, p = 0
% 	(pow_logN(n, p / 2, modulo) * pow_logN(n, p / 2, modulo)) % modulo, p % 2 = 0
% 	(((pow_logN(n, p / 2, modulo) * pow_logN(n, p / 2, modulo)) % modulo) * n) % modulo, p % 2 = 1

% pow_logN(N - number, P - number, Modulo - number, Result - number)
% pow_logN(i, i, i, o)

pow_logN(N, 1, _, N).
pow_logN(_, 0, _, 1).
pow_logN(N, P, Modulo, Result):-
    P mod 2 =:= 0,
    P1 is P / 2,
    pow_logN(N, P1, Modulo, Result1),
    Result is (Result1 * Result1) mod Modulo.
pow_logN(N, P, Modulo, Result):-
    P mod 2 =:= 1,
    P1 is (P - 1) / 2,
    pow_logN(N, P1, Modulo, Result1),
    Result is (((Result1 * Result1) mod Modulo) * N) mod Modulo.

