% 10.
% a. Define a predicate to test if a list of an integer elements has a "valley" aspect (a set has a "valley" aspect if
% elements decreases up to a certain point, and then increases.
% eg: 10 8 6 9 11 13 – has a “valley” aspect
% b. Calculate the alternate sum of list’s elements (l1 - l2 + l3 ...).

% a

% Model matematic:
% valley(l1...ln, f) =
% 	true, n = 1 and f = 0
% 	valley(l2...ln, 0), l1 < l2
% 	valley(l2...ln, 1), l1 > l2
% 	false, otherwise

% valley(L:list, F:number)
% valley(i, i)

valley([_], 0).
valley([H1, H2|T], _) :- H1 < H2, 
    valley([H2|T], 0), !.
valley([H1, H2|T], 1) :- H1 > H2,
    valley([H2|T], 1), !.

% b

% Model matematic:
% alternative_sum(l1...ln) =
% 	0, n = 0
% 	l1, n = 1
% 	l1 - l2 + alternative_sum(l3...ln), otherwise

% alternative_sum(L:list, R:number)
% alternative_sum(i, o)

alternative_sum([], 0).
alternative_sum([H], H).
alternative_sum([H1, H2|T], R) :-
    alternative_sum(T, R1),
    R is H1 - H2 + R1.