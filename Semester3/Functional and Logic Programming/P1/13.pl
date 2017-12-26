% 13.
% a. Transform a list in a set, in the order of the last occurrences of elements. 
% Eg.: [1,2,3,1,2] is transformed in [3,1,2].
% b. Define a predicate to determine the greatest common divisor of all numbers in a list

% a

% Model matematic:
% count_occurences(l1...ln, e) =
% 	0, n = 0
% 	1 + count_occurences(l2...ln, e), l1 = e
% 	count_occurences(l2...ln, e), otherwise
 	
% count_occurences(L:list, E:number, R:number)
% count_occurences(i, i, o)

count_occurences([], _, 0).
count_occurences([H|T], E, R) :- H =:= E,
    count_occurences(T, E, R1),
    R is R1 + 1.
count_occurences([H|T], E, R) :- H =\= E,
    count_occurences(T, E, R).


% Model matematic:
% list_to_set(l1...ln) =
% 	[], n = 0
% 	l1 + list_to_set(l2...ln), count_occurences(l2...ln, l1) = 0
% 	list_to_set(l2...ln), otherwise
 	
% list_to_set(L:list, R:list)
% list_to_set(i, o)

list_to_set([], []) :- !.
list_to_set([H|T], [H|R]) :- 
    count_occurences(T, H, RC),
    RC =:= 0,
    list_to_set(T, R), !.
list_to_set([_|T], R) :-
    list_to_set(T, R).

% b
 
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
% gcd_list(l1...ln) =
% 	l1, n = 1
% 	gcd(l1, gcd_list(l2...ln)), otherwise
	
% gcd_list(L:list, R:number)
% gcd_list(i, o)

gcd_list([H], H).
gcd_list([H|T], R) :- 
    gcd_list(T, R1),
    gcd(R1, H, R).