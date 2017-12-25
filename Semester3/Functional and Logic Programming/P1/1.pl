% 1.
% a. Write a predicate to determine the lowest common multiple of a list formed from integer numbers.
% b. Write a predicate to add a value v after 1-st, 2-nd, 4-th, 8-th, … element in a list.

% a

% Model matematic:
% gcd(a, b) =
%	a, b = 0
%	b, a = 0
%	gcd(a % b, b), a >= b
%	gcd(a, b % a), a < b

% gcd(A:number, B:number, R:number)
% gcd(i, i, o)

gcd(0, B, B) :- !.
gcd(A, 0, A) :- !.
gcd(A, B, R) :- A >= B,
    A1 is A mod B,
    gcd(A1, B, R), !.
gcd(A, B, R) :- A < B,
    B1 is B mod A,
    gcd(A, B1, R), !.

% Model matematic:
% lcm(a, b) = a * b / gcd(a, b)

% lcm(A:number, B:number, R:number)
% lcm(i, i, o)

lcm(A, B, R) :-
    gcd(A, B, RGCD),
    R is A * B / RGCD.

% b

% Model matematic:
% insert_pow(l1...ln, v, pos, index) =
%	[], n = 0
%	l1 + v + insert_pow(l2...ln, v, pos * 2, index + 1), index = pos
%	l1 + insert_pow(l2...ln, v, pos, index + 1), pos != index

% insert_pow(L:list, V:number, POS:number, INDEX:number, R:list)
% insert_pow(i, i, i, i, o)

insert_pow([], _, _, _, []).
insert_pow([H|T], V, POS, INDEX, [H, V|R]) :- POS =:= INDEX,
    New_pos is POS * 2,
    New_index is INDEX + 1,
    insert_pow(T, V, New_pos, New_index, R).
insert_pow([H|T], V, POS, INDEX, [H|R]) :- POS =\= INDEX,
    New_index is INDEX + 1,
    insert_pow(T, V, POS, New_index, R).

% insert(L:list, V:number, R:list)
% insert(i, i, o)

insert(L, V, R) :- insert_pow(L, V, 1, 1, R).
