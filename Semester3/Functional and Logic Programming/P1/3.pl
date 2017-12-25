% 3.
% a. Define a predicate to remove from a list all repetitive elements.
% Eg.: l=[1,2,1,4,1,3,4] => l=[2,3])
% b. Remove all occurrence of a maximum value from a list on integer numbers.

% a

% Model matematic:
% remove_occurences(l1...ln, e) =
%    [], n = 0
%    l1 + remove_occurences(l2...ln, e), l1 != e
%    remove_occurences(l2...ln, e), l1 = e

% remove_occurences(L:list, E:number, R:list)
% remove_occurences(i, i, o)

remove_occurences([], _, []).
remove_occurences([H|T], E, R) :- H =:= E,
    remove_occurences(T, E, R).
remove_occurences([H|T], E, [H|R]) :- H =\= E,
    remove_occurences(T, E, R).

% Model matematic:
% count_occurences(l1...ln, e) =
% 	0, n = 0
% 	1 + count_occurences(l2...ln, e), e = l1
% 	count_occurences(l2...ln, e), e != l1

% count_occurences(L:list, E:number, R:number)
% count_occurences(i, i, o)

count_occurences([], _, 0).
count_occurences([H|T], E, R) :- H =:= E,
    count_occurences(T, E, R1),
    R is R1 + 1.
count_occurences([H|T], E, R) :- H =\= E,
    count_occurences(T, E, R).

% Model matematic:
% remove_repetitive(l1...ln) =
% 	[], n = 0
% 	l1 + remove_repetitive(l2...ln), count_occurences(l1...ln, l1) = 1
% 	remove_repetitive(remove_occurences(l1...ln, l1)), count_occurences(l1...ln, l1) != 1
	
% remove_repetitive(L:list, R:list)
% remove_repetitive(i, o)

remove_repetitive([], []).
remove_repetitive([H|T], [H|R]) :-
    count_occurences([H|T], H, RC),
    RC =:= 1,
    remove_repetitive(T, R).
remove_repetitive([H|T], R) :-
    count_occurences([H|T], H, RC),
    RC =\= 1,
    remove_occurences([H|T], H, RO),
    remove_repetitive(RO, R).

% b

% Model matematic:
% maxim_number(a, b) = 
% 	a, a >= b
% 	b a < b

% maxim_number(A:number, B:number, R:number)
% maxim_number(i, i, o)

maxim_number(A, B, A) :- A >= B.
maxim_number(A, B, B) :- A < B.

% Model matematic:
% maxim_list(l1...ln) =
% 	l1, n = 1
% 	maxim_number(l1, maxim_list(l2...ln))
	
% maxim_list(L:list, R:number)
% maxim_list(i, o)

maxim_list([H], H).
maxim_list([H|T], R) :- 
    maxim_list(T, RM),
    maxim_number(H, RM, R).

% remove_maxim(l1...ln) =
% 	remove_occurences(l1...ln, maxim_list(l1...ln))			

% remove_maxim(L:list, R:list)
% remove_maxim(i, o)

remove_maxim(L, R) :-
    maxim_list(L, RM),
    remove_occurences(L, RM, R).